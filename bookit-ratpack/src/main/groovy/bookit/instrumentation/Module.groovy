package bookit.instrumentation

import com.google.inject.Scopes
import com.google.inject.AbstractModule
import graphql.execution.instrumentation.Instrumentation

/**
 * Module configuring GraphQL instrumentation
 *
 * @since 0.1.0
 */
class Module extends AbstractModule {
  @Override
  void configure() {
    bind(Instrumentation)
      .toProvider(InstrumentationProvider)
      .in(Scopes.SINGLETON)
  }
}
