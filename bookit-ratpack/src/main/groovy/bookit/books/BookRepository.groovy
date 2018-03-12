package bookit.books

import groovy.sql.Sql
import javax.inject.Inject

/**
 * Repository responsible to access book related data from the
 * database
 *
 * @since 0.1.0
 */
class BookRepository {

  /**
   * Database access
   *
   * @since 0.1.0
   */
  @Inject
  Sql sql

  /**
   * Returns a list of related information about books
   *
   * @return a list of books
   * @since 0.1.0
   */
  List<Map> list(Integer offset, Integer maxRows) {
    String query = '''
      SELECT
        id,
        created,
        published,
        title,
        image_uri as imageUri,
        short_description as shortDescription,
        author_id as authorId
      FROM book
    '''

    return sql.rows(query, offset, maxRows) as List<Map>
  }
}
