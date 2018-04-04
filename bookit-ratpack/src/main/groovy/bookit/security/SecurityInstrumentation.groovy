package bookit.security

import gql.DSL
import ratpack.handling.Context
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import graphql.GraphQLError
import graphql.schema.GraphQLType
import graphql.schema.GraphQLFieldDefinition
import graphql.execution.instrumentation.NoOpInstrumentation
import graphql.execution.instrumentation.parameters.InstrumentationFieldFetchParameters
import groovy.util.logging.Slf4j
import groovy.transform.TupleConstructor

/**
 * Naive authorization mechanism based on `graphql-java`
 * instrumentation behavior
 *
 * @since 0.1.0
 */
@Slf4j
@TupleConstructor
class SecurityInstrumentation extends NoOpInstrumentation {

  SecurityService securityService

  @Override
  DataFetcher<?> instrumentDataFetcher(DataFetcher<?> dataFetcher, InstrumentationFieldFetchParameters params) {
    log.debug('Instrumenting DataFetcher to check security constraints')

    DataFetchingEnvironment environment = params.environment
    Context context = environment.context as Context
    GraphQLType parentType = environment.parentType
    GraphQLFieldDefinition fieldDefinition = environment.fieldDefinition

    if (!securityService.requiresAuthorization(parentType.name, fieldDefinition.name)) {
      return dataFetcher
    }

    return context
      .header('Authorization')
      .flatMap(this.&getToken)
      .flatMap(securityService.&checkAuthentication)
      .filter(securityService.checkAuthorization(parentType.name, fieldDefinition.name))
      .map { dataFetcher }
      .orElse(notAuthorized(params))
  }

  /**
   * Returns a {@link DataFetcher} which raises a security exception
   *
   * @params parameters of the current execution
   * @return a {@link DataFetcher} raising an {@link GraphQLError}
   * @since 0.1.0
   */
  DataFetcher notAuthorized(InstrumentationFieldFetchParameters params) {
    return DSL.errorFetcher(params) {
      message 'Forbidden Resource'
      extensions(i18n: 'ERROR.SECURITY.FORBIDDEN')
    }
  }

  /**
   * Cleans up authorization information found in the authorization
   * header. It should only return the token part.
   *
   * @param authorization content found in the authorization header
   * @return an {@link Optional} containing the token found in the
   * authorization header
   * @since 0.1.0
   */
  Optional<String> getToken(String authorization) {
    return Optional.ofNullable(authorization.trim() - 'JWT ')
  }
}
