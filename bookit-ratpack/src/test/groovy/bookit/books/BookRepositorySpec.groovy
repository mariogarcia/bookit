package bookit.books

import static Fixtures.createBook

import org.neo4j.graphdb.Result
import bookit.test.Neo4jSpec

class BookRepositorySpec extends Neo4jSpec {

  void 'list existent books'() {
    given: 'the repository we want to test'
    def repository = new BookRepository()

    when: 'creating a new book'
    (1..10).each(this.&createJavaBook)

    and: 'listing all available books'
    def result = service.execute(repository.listQuery)

    then: 'we should only get the one we created'
    result.size() == 10
  }

  void 'count number of books'() {
    given: 'the repository we want to test'
    def repository = new BookRepository()

    when: 'creating a new book'
    (1..5).each(this.&createJavaBook)

    and: 'counting all available books'
    def result = service.execute(repository.countBooksQuery)
      .first()

    then: 'we should only get the one we created'
    result.no == 5
  }

  Result createJavaBook(Integer version) {
    return service.execute(createBook("Java $version"))
  }

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
    result.size() == 10
  }
}
