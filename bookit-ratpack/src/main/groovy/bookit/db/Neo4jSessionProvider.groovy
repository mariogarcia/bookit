package bookit.db

import javax.inject.Inject
import javax.inject.Provider
import org.neo4j.driver.v1.Driver
import org.neo4j.driver.v1.Session

class Neo4jSessionProvider implements Provider<Session> {

  @Inject
  Driver driver

  @Override
  Session get() {
    return driver.session()
  }
}
