package bookit.test

import org.junit.Before
import org.junit.After
import org.neo4j.test.TestGraphDatabaseFactory
import org.neo4j.graphdb.GraphDatabaseService

class Neo4jTestCaseBase {

  GraphDatabaseService service

  @Before
  void setup() {
    service = new TestGraphDatabaseFactory().newImpermanentDatabase(new File("build/test/neo4j"))
  }

  @After
  void tearDown() {
    service.shutdown()
  }
}
