package bookit.books

import io.vavr.Tuple2
import javax.inject.Inject
import graphql.schema.DataFetchingEnvironment

/**
 * Service responsible to fetch book's related data
 *
 * @since 0.1.0
 */
class BookService {

  /**
   * Database access to books related data
   *
   * @since 0.1.0
   */
  @Inject
  BookRepository repository

  /**
   * Lists all books
   *
   * @param env data coming from the request
   * @return a list of books
   * @since 0.1.0
   */
  List<Map> list(DataFetchingEnvironment env) {
    Tuple2<Integer, Integer> args = Selectors.list(env.arguments)

    return repository.list(args._1, args._2)
  }

  /**
   * Returns the number of available books
   *
   * @param env data coming from the request
   * @return the number of available books
   * @since 0.1.0
   */
  @SuppressWarnings('UnusedMethodParameter')
  Integer getBookCount(DataFetchingEnvironment env) {
    return repository.countBooks()
  }

  /**
   * Returns the number of available authors
   *
   * @param env data coming from the request
   * @return the number of available authors
   * @since 0.1.0
   */
  @SuppressWarnings('UnusedMethodParameter')
  Integer getAuthorCount(DataFetchingEnvironment env) {
    return repository.countAuthors()
  }

  /**
   * Returns the number of different technologies
   *
   * @param env data coming from the request
   * @return the number of technologies listed
   * @since 0.1.0
   */
  @SuppressWarnings('UnusedMethodParameter')
  Integer getTechnologiesCount(DataFetchingEnvironment env) {
    return repository.countTechnologies()
  }
}
