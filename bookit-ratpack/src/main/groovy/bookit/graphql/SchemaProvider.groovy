package bookit.graphql

import static gql.DSL.mergeSchemas

import graphql.schema.GraphQLSchema

import javax.inject.Inject
import javax.inject.Provider
import bookit.books.BookService
import bookit.security.SecurityService

/**
 * Provides a singleton instance of the {@link GraphQLSchema} type
 *
 * @since 0.1.0
 */
class SchemaProvider implements Provider<GraphQLSchema> {

  @Inject BookService bookService
  @Inject SecurityService securityService

  @Override
  @SuppressWarnings('DuplicateStringLiteral')
  GraphQLSchema get() {
    return mergeSchemas {
      byResource('graphql/Login.graphql')
      byResource('graphql/Books.graphql')
      byResource('graphql/Stats.graphql') {
        mapType('Stats') {
          link('books', bookService.&getBookCount)
          link('authors', bookService.&getAuthorCount)
        }
      }
      byResource('graphql/Schema.graphql') {
        mapType('Query') {
          link('books', bookService.&list)
          link('login', securityService.&login)
          link('stats', Utils.DELEGATE_TO_FIELDS)
        }
      }
    }
  }
}
