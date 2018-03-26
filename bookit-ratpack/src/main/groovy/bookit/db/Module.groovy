package bookit.db

import com.google.inject.AbstractModule
import javax.sql.DataSource
import com.google.inject.Scopes
import org.neo4j.driver.v1.Driver
import org.neo4j.driver.v1.Session

/**
 * Creates a new {@link DataSource}
 * @since 0.1.0
 */
class Module extends AbstractModule {
  @Override
  protected void configure() {
    bind(DataSource).toProvider(HikariDataSourceProvider)

    bind(Driver)
      .toProvider(Neo4jDriverProvider)
      .in(Scopes.SINGLETON)

    bind(Session)
      .toProvider(Neo4jSessionProvider)
  }
}
