package bookit.instrumentation

import javax.inject.Inject
import javax.inject.Provider
import bookit.security.SecurityService
import bookit.security.SecurityInstrumentation
import graphql.execution.instrumentation.Instrumentation
import graphql.execution.instrumentation.ChainedInstrumentation

/**
 *
 * @since 0.1.0
 */
class InstrumentationProvider implements Provider<Instrumentation> {

  /**
   * Used to check authentication token
   *
   * @since 0.1.0
   */
  @Inject
  SecurityService securityService

  @Override
  Instrumentation get() {
    List<Instrumentation> instrumentations = [
      new SecurityInstrumentation(securityService),
      new VavrInstrumentation()
    ] as List<Instrumentation>

    return new ChainedInstrumentation(instrumentations)
  }
}
