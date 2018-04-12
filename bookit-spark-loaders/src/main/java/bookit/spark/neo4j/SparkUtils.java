package bookit.spark.neo4j;

import io.vavr.control.Try;
import org.apache.spark.sql.Encoder;
import org.apache.spark.sql.Encoders;
import org.apache.spark.api.java.function.MapFunction;

/**
 * A set of handy functions when transforming data with Spark
 *
 * @since 0.1.0
 */
public class SparkUtils {

  /**
   * This method will create an {@link Encoder} capable of serializing
   * bean-like objects of the type passed as parameter
   *
   * @param clazz the type of object to serialize
   * @return an {@link Encoder} capable of serializing objects of the
   * given type
   * @since 0.1.0
   */
  public static <T> Encoder<T> serializeTo(Class<T> clazz) {
    return Encoders.bean(clazz);
  }

  /**
   * Wraps a given function to avoid propagating a potential
   * exception. In case the exception is produced, the wrapper will
   * return the default value provided.
   *
   * @param fn the potential unsafe function
   * @param defaultValue default value in case of exception
   * @return a safer function that will return the default value if
   * any exception occurs
   * @since 0.1.0
   */
  public static <I, O> MapFunction<I, O> safely(MapFunction<I, O> fn, O defaultValue) {
    return (I input) -> {
      return Try
        .of(() -> fn.call(input))
        .getOrElse(defaultValue);
    };
  }
}
