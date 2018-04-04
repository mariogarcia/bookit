package bookit.db

import static org.neo4j.driver.v1.AuthTokens.basic
import static org.neo4j.driver.v1.GraphDatabase.driver

import javax.inject.Inject
import javax.inject.Provider
import bookit.config.AppConfig
import org.neo4j.driver.v1.Driver

class Neo4jDriverProvider implements Provider<Driver> {

  @Inject
  AppConfig config

  @Override
  Driver get() {
    return driver(
      config.neo.uri,
      basic(config.neo.username, config.neo.password)
    )
  }
}
