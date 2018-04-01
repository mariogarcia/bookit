package bookit.books

/**
 * Cypher statements to create book related nodes
 *
 * @since 0.1.0
 */
class Fixtures {

  /**
   * Creates a book with a given title
   *
   * @param title the title of the book
   * @return the query string
   * @since 0.1.0
   */
  static String createBook(String title) {
    return """
    MERGE (:Book {title: \"$title\"})
    """
  }

  /**
   * Creates a book with a given title and also
   * a given author
   *
   * @param title the title of the book
   * @param authorName the name of the author
   * @return the query string
   * @since 0.1.0
   */
  static String createBook(String title, String authorName) {
    return """
    MERGE (author:Author {name: \"$authorName\"})
    MERGE (book:Book {title: \"$title\"})
    MERGE (author)-[:WROTE]->(book)
    """
  }
}
