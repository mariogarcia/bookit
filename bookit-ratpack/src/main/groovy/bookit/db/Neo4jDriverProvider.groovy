package bookit.db

import javax.inject.Inject
import javax.inject.Provider
import bookit.config.AppConfig
import org.neo4j.driver.v1.Driver
import org.neo4j.driver.v1.GraphDatabase
import org.neo4j.driver.v1.AuthTokens

class Neo4jDriverProvider implements Provider<Driver> {

  @Inject
  AppConfig config

  Driver get() {
    return GraphDatabase.driver(
      config.neo.uri,
      AuthTokens.basic(
        config.neo.username,
        config.neo.password
      )
    )
  }
}
