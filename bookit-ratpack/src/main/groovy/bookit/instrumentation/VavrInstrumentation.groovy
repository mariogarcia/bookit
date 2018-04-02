package bookit.instrumentation

import groovy.util.logging.Slf4j
import io.vavr.control.Option
import io.vavr.control.Either
import graphql.GraphQLError
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import graphql.execution.ExecutionPath
import graphql.execution.ExecutionContext
import graphql.execution.instrumentation.NoOpInstrumentation
import graphql.execution.instrumentation.parameters.InstrumentationFieldFetchParameters

/**
 * This instrumentation makes possible to use VAVR {@link Either} type
 * as a {@link DataFetcher} return type.
 *
 * This instrumentation intercepts {@link Either.Left} instances of
 * type {@link GraphQLError} and adds that error to the current {@link
 * ExecutionContext}
 *
 * @since 0.1.0
 */
@Slf4j
class VavrInstrumentation extends NoOpInstrumentation {

  /**
   * DataFetcher that returns nothing (null). In case the original
   * fetcher had an error then the resulting {@link DataFetcher}
   * should return nothing
   *
   * @since 0.1.0
   */
  static final DataFetcher<?> EMPTY_DATA_FETCHER = { DataFetchingEnvironment env -> null } as DataFetcher<?>

  @Override
  public DataFetcher<?> instrumentDataFetcher(
    DataFetcher<?> dataFetcher,
    InstrumentationFieldFetchParameters parameters) {
    log.debug('Instrumenting DataFetcher to intercept Vavr Either return types')

    DataFetchingEnvironment environment = parameters.getEnvironment()
    ExecutionContext executionContext = parameters.getExecutionContext()
    Object o = dataFetcher.get(environment)

    return Option
      .of(o)
      .flatMap(this.handleResult(executionContext))
      .map(this.&createValueDataFetcher)
      .getOrElse(EMPTY_DATA_FETCHER)
  }

  Closure<Option<?>> handleResult(ExecutionContext executionContext) {
    return { Object o ->
      switch (o) {
        case Either:
          return handleEither(executionContext, o as Either)
        default:
          return Option.of(o)
      }
    }
  }

  Option handleEither(ExecutionContext executionContext, Either either) {
    return either
      .peekLeft { Object o ->
        switch (o) {
          case GraphQLError:
            executionContext.addError(o as GraphQLError, ExecutionPath.rootPath())
            return
          default:
            log.info('DataFetcher returns an Either.Left type other than GraphQLError')
        }
      }
      .toOption()
  }

  DataFetcher<?> createValueDataFetcher(Object o) {
    return { DataFetchingEnvironment env ->
      return o
    } as DataFetcher<?>
  }
}
