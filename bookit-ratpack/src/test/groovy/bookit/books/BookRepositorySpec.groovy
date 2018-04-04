package bookit.books

import static Fixtures.createBook
import static groovy.test.GroovyAssert.assertTrue

import org.junit.Test
import org.neo4j.graphdb.Result
import bookit.test.Neo4jTestCaseBase

class BookRepositorySpec extends Neo4jTestCaseBase {

  @Test
  void 'list existent books'() {
    given: 'the repository we want to test'
    def repository = new BookRepository()

    when: 'creating a new book'
    (1..10).each(this::createJavaBook)

    and: 'listing all available books'
    def result = service.execute(repository.listQuery)

    then: 'we should only get the one we created'
    assertTrue result.size() == 10
  }

  @Test
  void 'count number of books'() {
    given: 'the repository we want to test'
    def repository = new BookRepository()

    when: 'creating a new book'
    (1..5).each(this::createJavaBook)

    and: 'counting all available books'
    def result = service.execute(repository.countBooksQuery)
      .first()

    then: 'we should only get the one we created'
    assertTrue result.no == 5
  }

  Result createJavaBook(Integer version) {
    return service.execute(createBook("Java $version"))
  }

  @Test
  void 'find books of a given author'() {
    given: 'a list of books of a given author'
    (1..10).each { n ->
      service.execute(createBook("Java $n", "John Doe"))
    }

    when: 'finding books of a given author'
    def repository = new BookRepository()
    def result = service.execute(
      repository.booksByAuthorNameQuery,
      [name: 'John Doe'])

    then: 'we should get his books'
    assertTrue result.size() == 10
  }
}
