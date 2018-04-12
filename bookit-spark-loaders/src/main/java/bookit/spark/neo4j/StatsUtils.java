package bookit.spark.neo4j;

import org.neo4j.driver.v1.summary.ResultSummary;
import org.neo4j.driver.v1.summary.SummaryCounters;

public class StatsUtils {

  public static Stats of(ResultSummary summary) {
    Stats stats = new Stats();
    SummaryCounters counters = summary.counters();
    stats.setTotal(counters.relationshipsCreated());
    return stats;
  }

  public static Stats empty() {
    Stats stats = new Stats();
    stats.setTotal(0);
    return stats;
  }

  public static Stats combine(Stats left, Stats right) {
    Stats stats = new Stats();
    stats.setTotal(left.getTotal() + right.getTotal());
    return stats;
  }
}
