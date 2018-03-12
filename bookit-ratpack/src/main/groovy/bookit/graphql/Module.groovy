package bookit.graphql

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import graphql.schema.GraphQLSchema

/**
 *
 */
class Module extends AbstractModule {

  @Override
  protected void configure() {
    bind(GraphQLSchema).toProvider(SchemaProvider)
  }
}
