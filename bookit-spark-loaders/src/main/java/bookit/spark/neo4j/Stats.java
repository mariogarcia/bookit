package bookit.spark.neo4j;

import java.io.Serializable;

public class Stats implements Serializable {

  private Integer total;

  public void setTotal(Integer total) {
    this.total = total;
  }

  public Integer getTotal() {
    return this.total;
  }
}
