package bookit.security

import graphql.execution.instrumentation.NoOpInstrumentation

import java.util.concurrent.CompletableFuture
import ratpack.handling.Context
import graphql.schema.DataFetcher
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
   * Tree of Types/Permissions
   *
   * @since 0.1.0
   */
  Map<String, List<String>> PERMISSIONS = [
    Query: ['ANONYMOUS'],
    Login: ['ANONYMOUS']
  ]

  @Override
  DataFetcher<?> instrumentDataFetcher(DataFetcher<?> dataFetcher, InstrumentationFieldFetchParameters parameters) {
    // Getting Ratpack's context
    Context context = parameters.environment.context as Context

    // Checking permissions for current retrieved type
    List<String> permissions = PERMISSIONS
      .find { k, v ->
        k == parameters.environment.parentType.name
      }?.value

    // If if can be accessed anonymously then nothing happens
    if ('ANONYMOUS' in permissions) {
      return dataFetcher
    }

    // If not, user must be authorized
    return context
      .header('Authorization')
      .map { dataFetcher }
      .orElse({ env ->
        throw new I18nException('You Shall Not Pass', 'ERROR.SECURITY.NOT_ALLOWED')
      } as DataFetcher)
  }
}