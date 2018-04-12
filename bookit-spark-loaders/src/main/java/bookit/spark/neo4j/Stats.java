package bookit.spark.neo4j;

import java.io.Serializable;

/**
 * Object collecting Neo4j execution statistics
 *
 * @since 0.1.0
 */
public class Stats implements Serializable {

  /**
   * Number of elements added to database
   *
   * @since 0.1.0
   */
  private Integer total;

  /**
   * Sets the total number of elements added to the Neo4j database
   *
   * @param total number of elements added to db
   * @since 0.1.0
   */
  public void setTotal(Integer total) {
    this.total = total;
  }

  /**
   * Returns the total number of elements added to the Neo4j database
   *
   * @return number of elements added to db
   * @since 0.1.0
   */
  public Integer getTotal() {
    return this.total;
  }
}
