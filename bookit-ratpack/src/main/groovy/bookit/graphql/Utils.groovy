package bookit.graphql

import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment

/**
 * GraphQL related utility functions
 *
 * @since 0.1.0
 */
class Utils {

  /**
   * Returns a {@link DataFetcher} which returns an empty map. It can
   * be used to indicate that fields should be retrieved from their
   * own fetchers
   *
   * @return a {@link DataFetcher} returning an empty map
   * @since 0.1.0
   */
  static final DataFetcher DELEGATE_TO_FIELDS = { DataFetchingEnvironment env ->
    return [:]
  } as DataFetcher
}
