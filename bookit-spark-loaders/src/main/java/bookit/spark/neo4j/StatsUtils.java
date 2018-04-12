package bookit.spark.neo4j;

import org.neo4j.driver.v1.summary.ResultSummary;
import org.neo4j.driver.v1.summary.SummaryCounters;

/**
 * Set of functions to create, handle instances of type {@link Stats}
 *
 * @since 0.1.0
 */
public class StatsUtils {

  /**
   * Converts an instance of type {@link ResultSummary} which is not
   * serializable to an instance of type {@link Stats}
   *
   * @param summary summary provided by Neo4j
   * @return an instance of {@link Stats}
   * @since 0.1.0
   */
  public static Stats of(ResultSummary summary) {
    Stats stats = new Stats();
    SummaryCounters counters = summary.counters();
    stats.setTotal(counters.relationshipsCreated());
    return stats;
  }

  /**
   * Returns an empty {@link Stats} instance
   *
   * @return an instance of {@link Stats} with no data
   * @since 0.1.0
   */
  public static Stats empty() {
    Stats stats = new Stats();
    stats.setTotal(0);
    return stats;
  }

  /**
   * Adds up partial results from two instances of {@link Stats}. Very
   * handy when reducing a dataset of {@link Stats}
   *
   * @param left left side {@link Stats}
   * @param right right side {@link Stats}
   * @since 0.1.0
   */
  public static Stats combine(Stats left, Stats right) {
    Stats stats = new Stats();
    stats.setTotal(left.getTotal() + right.getTotal());
    return stats;
  }
}
