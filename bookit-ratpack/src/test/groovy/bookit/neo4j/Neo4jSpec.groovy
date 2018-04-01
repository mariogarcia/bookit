package bookit.neo4j

import spock.lang.Shared
import spock.lang.Specification
import org.neo4j.test.TestGraphDatabaseFactory
import org.neo4j.graphdb.GraphDatabaseService

class Neo4jSpec extends Specification {

  @Shared
  GraphDatabaseService service

  void setup() {
    service = new TestGraphDatabaseFactory().newImpermanentDatabase(new File("build/test/neo4j"))
  }

  void cleanup() {
    service.shutdown()
  }
}
