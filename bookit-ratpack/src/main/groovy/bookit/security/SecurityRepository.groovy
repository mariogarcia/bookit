package bookit.security

import javax.inject.Inject
import groovy.sql.Sql

class SecurityRepository {

  @Inject
  Sql sql

  Map login(String username, String password) {
    return [
      token: 'kkk',
      name: 'John'
    ]
  }
}
