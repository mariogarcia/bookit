package bookit.security

import javax.inject.Inject
import graphql.schema.DataFetchingEnvironment

class SecurityService {

  @Inject
  SecurityRepository repository

  Map login(DataFetchingEnvironment env) {
    String username = env.arguments.username?.toString()
    String password = env.arguments.password?.toString()

    return repository.login(username, password)
  }
}
