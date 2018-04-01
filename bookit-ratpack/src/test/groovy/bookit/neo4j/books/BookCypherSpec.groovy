package bookit.books

import bookit.neo4j.Neo4jSpec
import org.neo4j.graphdb.Result

class BookCypherSpec extends Neo4jSpec {

  void 'create book'() {
    given: 'a query of creating a single book'
    def query = '''
     CREATE (book:Book {title: "Java 9"})
    '''

    when: 'executing the statement'
      Result result = service.execute(query)

    then: 'we should not get any result'
    !result
  }

  void 'create and get book'() {
    given: 'a query of creating a single book'
    def create = '''
     CREATE (book:Book {title: "Java 9"})
    '''

    def retrieve = '''
     MATCH (book:Book {title: "Java 9"}) RETURN book.title as title
    '''

    when: 'executing the statement'
    Result createResult = service.execute(create)
    Result retrieveResult = service.execute(retrieve)

    then: 'we should not get anything from creation'
    !createResult

    and: 'we should get the title of the retrieved book'
    retrieveResult.first().title == "Java 9"
  }
}
