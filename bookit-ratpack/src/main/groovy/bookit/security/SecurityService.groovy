package bookit.security

import javax.inject.Inject
import graphql.schema.DataFetchingEnvironment

/**
 * Service responsible to check application security
 *
 * @since 0.1.0
 */
class SecurityService {

  /**
   * Accesses persisted security information
   *
   * @since 0.1.0
   */
  @Inject
  SecurityRepository repository

  /**
   * Handles cryptografic functions
   *
   * @since 0.1.0
   */
  @Inject
  CryptoService crypto

  /**
   * Returns basic information about a given user if the login
   * procedure has ended successfully
   *
   * @param env execution environment
   * @return a map with expected fields
   * @since 0.1.0
   */
  Map login(DataFetchingEnvironment env) {
    Map<String, String> credentials = env
      .arguments
      .credentials as Map<String, String>

    String password = crypto.hash(credentials.password)
    String username = credentials.username

    User user = repository.login(username, password)

    return Optional
      .ofNullable(user)
      .map(this.&createPayload)
      .orElse([:])
  }

  /*
   * Converts a {@link User} to the expected map for the login
   * response, including basic information such as the token
   *
   * @param user an instance of {@link User}
   * @return a map with basic login information
   * @since 0.1.0
   */
  private Map createPayload(User user) {
    return [
      name: user.username,
      token: crypto.createToken(user.username),
      roles: repository.getRoles(user.id)
    ]
  }
}
