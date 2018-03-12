package bookit.security

import com.google.inject.Scopes
import com.google.inject.AbstractModule

class Module extends AbstractModule {
  @Override
  void configure() {
    bind(SecurityService).in(Scopes.SINGLETON)
    bind(SecurityRepository).in(Scopes.SINGLETON)
  }
}
