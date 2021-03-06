package bookit.books

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
  @SuppressWarnings('UnusedMethodParameter')
  List<Map> list(Integer offset, Integer maxRows) {
    return session
      .run(listQuery)
      .list()
      .collect(Record::asMap)
  }

  /**
   * Query string representing a list of books
   *
   * @return a query string
   * @since 0.1.0
   */
  String getListQuery() {
    return '''
    MATCH (book:Book)
    RETURN
      book.title as title,
      book.thumbnail as thumbnail
    ORDER BY book.year DESC
    '''
  }

  /**
   * Returns the number of available books in the database
   *
   * @return the number of books found in db
   * @since 0.1.0
   */
  Integer countBooks() {
    return session
      .run(countBooksQuery)
      .single()
      .get('no', 0)
  }

  /**
   * Query string to get the number of books
   *
   * @return the query string
   * @since 0.1.0
   */
  String getCountBooksQuery() {
    return '''
    MATCH (b:Book) RETURN count(b) as no
    '''
  }

  /**
   * Returns the number of available technologies in the database
   *
   * @return the number of technologies found in db
   * @since 0.1.0
   */
  Integer countTechnologies() {
    return session
      .run(countTechnologiesQuery)
      .single()
      .get('no', 0)
  }

  /**
   * Query string to get the number of technologies
   *
   * @return the query string
   * @since 0.1.0
   */
  String getCountTechnologiesQuery() {
    return '''
    MATCH (t:Technology) RETURN count(t) as no
    '''
  }

  /**
   * Returns the number of available authors in the database
   *
   * @return the number of authors found in db
   * @since 0.1.0
   */
  Integer countAuthors() {
    return session
      .run(countAuthorsQuery)
      .single()
      .get('no', 0)
  }

  /**
   * Query string to get the number of authors
   *
   * @return the query string
   * @since 0.1.0
   */
  String getCountAuthorsQuery() {
    return '''
    MATCH (a:Author) RETURN count(a) as no
    '''
  }

  /**
   * Lists all books by a given author
   *
   * @param authorName the name of the author
   * @return a list of book nodes
   * @since 0.1.0
   */
  List<Map> findAllByAuthorName(String authorName) {
    return session
      .run(booksByAuthorNameQuery, [name: authorName] as Map<String, Object>)
      .list()
      .collect(Record::asMap)
  }

  /**
   * Query string to get a list of books by the name of the author
   *
   * @return the query string
   * @since 0.1.0
   */
  String getBooksByAuthorNameQuery() {
    return '''
    MATCH
      (author:Author {name: $name}),
      (books:Book)<-[:WROTE]-(author)
    RETURN
      books
    '''
  }
}
