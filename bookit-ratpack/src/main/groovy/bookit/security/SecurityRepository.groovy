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

    return new User(id: user.id, username: user.username)
  }

  /**
   * Retrieves all role names of a given user
   *
   * @param uuid user id
   * @return a list of role names
   * @since 0.1.0
   */
  List<String> getRoles(String uuid) {
    String query = '''
      SELECT
        role.rolename as rolename
      FROM bookit.app_role role JOIN bookit.app_user_role user_role ON
        role.id = user_role.role_id
      WHERE
        user_role.user_id = ?::uuid
    '''

    return sql
      .rows(query, uuid)
      .rolename as List<String>
  }
}
