package bookit.spark.books;

import static bookit.spark.common.ResourceUtils.getResourceURI;
import static java.lang.String.join;

import java.net.URISyntaxException;
import org.apache.spark.sql.SparkSession;

/**
 * Reads a CSV file and loads its content in a Neo4j database
 *
 * @since 0.1.0
 */
public final class CsvLoader {

  private CsvLoader() {
    // To prevent JaCoCo to check class instantiation
  }

  /**
   * Main entry point
   *
   * @param args execution arguments
   * @since 0.1.0
   */
  public static void main(String args[]) throws URISyntaxException {
    final String fileURI = getResourceURI("/books.csv");

    SparkSession
      .builder()
      .getOrCreate()
      .read()
      .textFile(fileURI)
      .javaRDD()
      .map(CsvLoader::createStatement)
      .saveAsTextFile("/tmp/statements/");
  }

  /**
   * Converts a csv line to a Neo4j cypher statement
   *
   * @param line csv file containing a book's detail
   * @return an string containing a cypher statement
   * @since 0.1.0
   */
  public static String createStatement(String line) {
    String[] fields = line.split("\\|");

    String title = fields[0];
    String area = fields[1];
    String isbn = fields[2];
    String image = fields[3];
    String month = fields[4];
    String year = fields[5];
    String author = fields[6];

    String areaStmt         = join("", "MERGE (tech:Technology {name: \"", area, "\"})");
    String bookStmt         = join("", "MERGE (book:Book {title: \"", title, "\"})");
    String authorStmt       = join("", "MERGE (author:Author {name: \"", author, "\"})");
    String relationshipStmt = join("", "MERGE (tech)-[:HAS]->(book)<-[:WROTE {month: ", month, ", year: ", year ,"}]-(author)");
    String stmt             = join("\n", areaStmt, bookStmt, authorStmt, relationshipStmt);

    return stmt;
  }
}
