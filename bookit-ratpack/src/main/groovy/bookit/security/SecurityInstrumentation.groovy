package bookit.security

import graphql.execution.instrumentation.NoOpInstrumentation
import javax.inject.Inject
import java.util.concurrent.CompletableFuture
import ratpack.handling.Context
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import graphql.schema.GraphQLType
import graphql.schema.GraphQLFieldDefinition
import graphql.execution.instrumentation.parameters.InstrumentationFieldFetchParameters
import graphql.execution.instrumentation.NoOpInstrumentation
import gql.exception.I18nException

/**
 * Naive authorization mechanism based on `graphql-java`
 * instrumentation behavior
 *
 * @since 0.1.0
 */
class SecurityInstrumentation extends NoOpInstrumentation {

  /**
   * Used to check authentication token
   *
   * @since 0.1.0
   */
  @Inject
  SecurityService securityService

  @Override
  DataFetcher<?> instrumentDataFetcher(DataFetcher<?> dataFetcher, InstrumentationFieldFetchParameters params) {
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
      .orElse(notAuthorized())
  }

  /**
   * Returns a {@link DataFetcher} which raises a security exception
   *
   * @return a {@link DataFetcher} raising an {@link I18nException}
   * @since 0.1.0
   */
  DataFetcher notAuthorized() {
    return { DataFetchingEnvironment env ->
      throw new I18nException('You Shall Not Pass', 'ERROR.SECURITY.NOT_ALLOWED')
    } as DataFetcher
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
