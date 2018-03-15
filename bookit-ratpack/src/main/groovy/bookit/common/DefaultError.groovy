package bookit.common

import graphql.ErrorType
import graphql.GraphQLError
import graphql.language.SourceLocation

/**
 * Exception adding i18n key to error extensions
 *
 * @since 0.3.0
 */
class DefaultError implements GraphQLError {

  Map<String,Object> extensions
  String message
  List<SourceLocation> locations
  ErrorType errorType
}
