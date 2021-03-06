package bookit.spark.books;

import static java.lang.String.join;
import static bookit.spark.common.Configuration.getSpark;
import static bookit.spark.common.ResourceUtils.getResourceURI;
import static bookit.spark.neo4j.SparkUtils.safely;
import static bookit.spark.neo4j.SparkUtils.serializeTo;

import bookit.spark.neo4j.Stats;
import bookit.spark.neo4j.StatsUtils;
import java.util.Map;
import java.util.HashMap;
import java.net.URISyntaxException;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.Encoders;
import org.apache.spark.SparkContext;
import org.neo4j.spark.Neo4jConfig;
import org.neo4j.spark.Neo4jDataFrame;
import org.apache.spark.api.java.function.MapFunction;

/**
 * Reads a CSV file and loads its content in a Neo4j database
 *
 * @since 0.1.0
 */
public final class CsvLoader {

  public static final Map<String, Object> EMPTY_MAP = new HashMap<String, Object>();

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
    String fileURI = getResourceURI("/books.csv");
    SparkSession session = getSpark();
    SparkContext context = session.sparkContext();
    Neo4jConfig neo4jConfig = Neo4jConfig.apply(context.getConf());
    MapFunction<String, Stats> stmtToStats = safely(executeCypher(neo4jConfig), StatsUtils.empty());

    Stats statistics = session
      .read()
      .format("csv")
      .option("header", "true")
      .option("delimiter", "|")
      .load(fileURI)
      .map(CsvLoader::toNeo4jStmt, Encoders.STRING())
      .map(stmtToStats, serializeTo(Stats.class))
      .reduce(StatsUtils::combine);

    System.out.println("TOTAL: " + statistics.getTotal());
  }

  /**
   * Given a {@link Neo4jConfig} it returns a function capable of
   * executing cypher statements against Neo4j
   *
   * @param neo4jConfig Neo4j configuration
   * @return a function executing a cypher statement against Neo4j
   * @since 0.1.0
   */
  static MapFunction<String, Stats> executeCypher(Neo4jConfig config) {
    return (String cypher) -> {
      return StatsUtils.of(Neo4jDataFrame.execute(config, cypher, EMPTY_MAP));
    };
  }

  /**
   * Converts a csv line to a Neo4j cypher statement
   *
   * @param line csv file containing a book's detail
   * @return an string containing a cypher statement
   * @since 0.1.0
   */
  public static String toNeo4jStmt(Row row) {
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
