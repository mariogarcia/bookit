package bookit.spark.common;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.neo4j.spark.Neo4JavaSparkContext;
import org.apache.spark.sql.SparkSession;

/**
 * Configuration related functions
 *
 * @since 0.1.0
 */
public final class Configuration {

  private Configuration() {
    // NOTHING
  }

  /**
   * Sets the initial Spark configuration
   *
   * @return an instance of {@link SparkConf}
   * @since 0.1.0
   */
  public static SparkConf getConf(String appName) {
    return new SparkConf()
      .setMaster("local")
      .setAppName(appName);
  }

  public static SparkContext getSparkContext(String appName) {
    SparkConf config = getConf(appName);
    SparkContext sparkContext = new SparkContext(config);

    return sparkContext;
  }

  public static SparkSession getSpark() {
    return SparkSession
      .builder()
      .master("local")
      .appName("unknown")
      .getOrCreate();
  }

  public static Neo4JavaSparkContext getNeo4jContext(SparkContext sparkContext) {
    return Neo4JavaSparkContext.neo4jContext(sparkContext);
  }
}
