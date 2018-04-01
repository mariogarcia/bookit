package bookit.books

import bookit.test.Neo4jSpec
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

  void 'create an author, a book and the relationship between them'() {
    given: 'a creation query'
    def create = '''
      CREATE
       (a:Author {name: "John"}),
       (b:Book {title: "Book"}),
       (a)-[:WROTE]->(b)
    '''

    def update = '''
      MERGE (a:Author {name: "John"})
      ON MATCH SET a.age = 34
    '''

    def retrieve = '''
      MATCH (a:Author {name: "John"}) RETURN a.age as age
    '''

    when: 'executing the query'
    service.execute(create)
    service.execute(update)

    and: 'extracting data'
    def result = service.execute(retrieve)

    then: 'we should get the correct age'
    result.first().age == 34

    and: 'there should be no more results'
    !result.hasNext()
  }

  void 'create two books shared by one of the authors'() {
    given: 'creation query'
    def create = '''
       CREATE
         (author1:Author {name: "John"}),
         (author2:Author {name: "Pete"}),
         (book1:Book {title: "Book1"}),
         (book2:Book {title: "Book2"}),
         (author1)-[:WROTE]->(book1),
         (author2)-[:WROTE]->(book1),
         (author2)-[:WROTE]->(book2)
    '''

    def getOtherBook = '''
    MATCH
      (author:Author {name: "John"}),
      (book:Book {title: "Book1"}),
      (otherBooks:Book)<-[:WROTE]-(others)-[:WROTE]->(book)<-[:WROTE]-(author)
    RETURN
      otherBooks.title as title
    '''

    when: 'executing the creation query'
    service.execute(create)

    and: 'the checking query'
    def result = service.execute(getOtherBook)

    then: 'we should get the other book written by other authors'
    result.first().title == 'Book2'

    and: 'there should be no more resuls'
    !result.hasNext()
  }
}
