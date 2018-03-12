package bookit.books

import javax.inject.Inject
import io.vavr.Tuple2
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
}
