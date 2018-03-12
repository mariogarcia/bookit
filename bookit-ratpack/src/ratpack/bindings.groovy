import static ratpack.groovy.Groovy.ratpack

import ratpack.groovy.sql.SqlModule
import gql.ratpack.GraphQLModule

import bookit.db.Module as DataSourceModule
import bookit.init.Module as InitModule
import bookit.graphql.Module as SchemaModule
import bookit.books.Module as BooksModule
import bookit.security.Module as SecurityModule

ratpack {
  bindings {
    module DataSourceModule
    module SchemaModule
    module SqlModule
    module GraphQLModule
    module InitModule
    module BooksModule
    module SecurityModule
  }
}
