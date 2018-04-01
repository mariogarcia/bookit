package bookit.books

import groovy.sql.Sql
import javax.inject.Inject
import org.neo4j.driver.v1.Session
import org.neo4j.driver.v1.Record

/**
 * Repository responsible to access book related data from the
 * database
 *
 * @since 0.1.0
 */
class BookRepository {

  /**
   * Graph database access
   *
   * @since 0.1.0
   */
  @Inject
  Session session

  /**
   * Returns a list of related information about books
   *
   * @return a list of books
   * @since 0.1.0
   */
  List<Map> list(Integer offset, Integer maxRows) {
    String query = """
     MATCH (book:Book)
     RETURN
         book.title as title,
         book.thumbnail as imageUri
     ORDER BY book.year DESC
    """

    return session
      .run(query)
      .list()
      .collect { Record record ->
        record.asMap()
      }
  }
}
