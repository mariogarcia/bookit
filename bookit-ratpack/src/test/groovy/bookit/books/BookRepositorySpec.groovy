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
    def result = execute(repository.listQuery)

    then: 'we should only get the one we created'
    result.size() == 10
  }

  void 'count number of books'() {
    given: 'the repository we want to test'
    def repository = new BookRepository()

    when: 'creating a new book'
    (1..5).each(this.&createJavaBook)

    and: 'counting all available books'
    def result = execute(repository.countBooksQuery)
      .first()

    then: 'we should only get the one we created'
    result.no == 5
  }

  Result createJavaBook(Integer version) {
    return execute(createBook("Java $version"))
  }
}
