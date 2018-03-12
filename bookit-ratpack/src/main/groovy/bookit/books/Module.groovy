package bookit.books

import com.google.inject.Scopes
import com.google.inject.AbstractModule

/**
 * Configures book related dependencies
 *
 * @since 0.1.0
 */
class Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(BookService).in(Scopes.SINGLETON)
    bind(BookRepository).in(Scopes.SINGLETON)
  }
}
