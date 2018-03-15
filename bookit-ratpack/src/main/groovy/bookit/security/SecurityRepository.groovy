package bookit.security

import javax.inject.Inject
import groovy.sql.Sql

/**
 * Accesses security related information persisted in the database
 *
 * @since 0.1.0
 */
class SecurityRepository {

  /**
   * Access to db
   *
   * @since 0.1.0
   */
  @Inject
  Sql sql

  /**
   * Gets basic information about a given user
   *
   * @param username login username
   * @param password login password
   * @return an instance of {@link User} with id and username
   * @since 0.1.0
   */
  User login(String username, String password) {
    String query = '''
      SELECT
        id, username
      FROM
        bookit.app_user
      WHERE
        username = :username AND
        password = :password
    '''

    Map user = sql.firstRow(query,
                            username: username,
                            password: password)

    return Optional
      .ofNullable(user)
      .map(this.&mapToUser)
      .orElse(null) as User
  }

  User mapToUser(Map user) {
    return new User(id: user.id, username: user.username)
  }

  /**
   * Retrieves all role names of a given user
   *
   * @param username user username
   * @return a list of role names
   * @since 0.1.0
   */
  List<String> getRoles(String username) {
    String query = '''
      SELECT
        r.rolename
      FROM
        app_user u
      JOIN
        app_user_role ur
      ON
        u.id = ur.user_id
      JOIN
        app_role r
      ON
        r.id = ur.role_id
      WHERE
        u.username = ?
    '''

    return sql
      .rows(query, username)
      .rolename as List<String>
  }
}
