package bookit.books

class Fixtures {

  static String createBook() {
    return '''
    MERGE (:Book {title: "Book"})
    '''
  }

  static String createBook(String title) {
    return """
    MERGE (:Book {title: \"$title\"})
    """
  }

  static String createBook(String title, String authorName) {
    return """
    MATCH
     (author:Author {name: \"$authorName\"})
    MERGE (book:Book {title: \"$title\"})
    MERGE (author)-[:WROTE]->(book)
    """
  }
}
