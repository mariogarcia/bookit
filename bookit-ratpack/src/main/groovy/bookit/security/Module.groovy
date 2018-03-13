package bookit.security

import com.google.inject.Scopes
import com.google.inject.AbstractModule
import com.auth0.jwt.algorithms.Algorithm

class Module extends AbstractModule {
  @Override
  void configure() {
    bind(Algorithm)
      .toProvider(AlgorithmProvider)
      .in(Scopes.SINGLETON)

    bind(CryptoService).in(Scopes.SINGLETON)
    bind(SecurityService).in(Scopes.SINGLETON)
    bind(SecurityRepository).in(Scopes.SINGLETON)
    bind(SecurityInstrumentation).in(Scopes.SINGLETON)
  }
}
