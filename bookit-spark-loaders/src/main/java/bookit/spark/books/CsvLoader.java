package bookit.spark.books;

import static java.lang.String.join;
import static bookit.spark.common.Configuration.getSpark;
import static bookit.spark.common.ResourceUtils.getResourceURI;

import java.net.URISyntaxException;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Encoders;

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
    final Dataset<String> statements = getSpark()
      .read()
      .format("csv")
      .option("header", "true")
      .option("delimiter", "|")
      .load(fileURI)
      .map(CsvLoader::toStatement, Encoders.STRING());

  }

  /**
   * Converts a csv line to a Neo4j cypher statement
   *
   * @param line csv file containing a book's detail
   * @return an string containing a cypher statement
   * @since 0.1.0
   */
  public static String toStatement(Row row) {
    String title = row.getString(0);
    String area = row.getString(1);
    String isbn = row.getString(2);
    String image = row.getString(3);
    String month = row.getString(4);
    String year = row.getString(5);
    String author = row.getString(6);

    String areaStmt         = join("", "MERGE (tech:Technology {name: \"", area, "\"})");
    String bookStmt         = join("", "MERGE (book:Book {title: \"", title, "\"})");
    String authorStmt       = join("", "MERGE (author:Author {name: \"", author, "\"})");
    String relationshipStmt = join("", "MERGE (tech)-[:HAS]->(book)<-[:WROTE {month: ", month, ", year: ", year ,"}]-(author)");
    String stmt             = join("\n", areaStmt, bookStmt, authorStmt, relationshipStmt);

    return stmt;
  }
}
