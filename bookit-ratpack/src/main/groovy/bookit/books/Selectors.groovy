package bookit.books

import groovy.transform.Memoized
import io.vavr.Tuple
import io.vavr.Tuple2

/**
 * Selectors gather data from GraphQL environment's arguments and
 * caches a possible subset of data used later throughout the app.
 *
 * @since 0.1.0
 */
class Selectors {

  /**
   * Caches arguments used to list books
   *
   * @param arguments arguments coming from GraphQL environment
   * @return a tuple of offset and maxRows
   * @since 0.1.0
   */
  @Memoized
  static Tuple2<Integer, Integer> list(Map<String,?> arguments) {
    Integer offset = arguments.offset as Integer
    Integer maxRows = arguments.maxRows as Integer

    return  Tuple.of(offset ?: 0, maxRows ?: 20)
  }
}
