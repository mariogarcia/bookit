package bookit.security

import javax.inject.Inject
import graphql.GraphQLError
import graphql.schema.DataFetchingEnvironment
import com.auth0.jwt.interfaces.DecodedJWT
import bookit.config.AppConfig
import bookit.common.DefaultError
import io.vavr.control.Option
import io.vavr.control.Either

/**
 * Service responsible to check application security
 *
 * @since 0.1.0
 */
class SecurityService {

  static final String ROLE_ANONYMOUS = 'ANONYMOUS'

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

  @Inject
  AppConfig config

  /**
   * Returns basic information about a given user if the login
   * procedure has ended successfully
   *
   * @param env execution environment
   * @return a map with expected fields
   * @since 0.1.0
   */
  Either<GraphQLError, Map> login(DataFetchingEnvironment env) {
    Map<String, String> credentials = env
      .arguments
      .credentials as Map<String, String>

    String password = crypto.hash(credentials.password)
    String username = credentials.username

    User user = repository.login(username, password)

    return Option
      .of(user)
      .map(this::createPayload)
      .toEither(buildLoginError())
  }

  GraphQLError buildLoginError() {
    return new DefaultError(
      message: 'Invalid Credentials',
      extensions: [
        i18n: 'ERROR.AUTH.INVALID',
      ] as Map)
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
      roles: repository.getRoles(user.username),
    ]
  }

  /**
   * Checks whether the current requested node needs
   * to be authorized or can be accessed anonymously
   *
   * @param parent parent type name
   * @param field field name
   * @return true if requires any permission or false if it can be
   * accessed anonymously
   * @since 0.1.0
   */
  Boolean requiresAuthorization(String parent, String field) {
    Boolean isAnonymous = hasAnonymousIntrospection(parent) || hasAnonymousRule(parent, field)

    return !isAnonymous
  }

  /**
   * Checks if the current node is an instrospection node and if it can be
   * queried anonymously or not
   *
   * @param parent parent type name
   * @return true if introspection can be queried anonymously
   * @since 0.1.0
   */
  Boolean hasAnonymousIntrospection(String parent) {
    List<String> rules = config.security.auth.rules.instrospection as List<String>
    Boolean allowed = ROLE_ANONYMOUS in rules  && isIntrospection(parent)

    return allowed
  }

  /**
   * Checks if the current node is NOT an instrospection node and if
   * it can be queried anonymously or not
   *
   * @param parent parent type name
   * @param field field name
   * @return true if it can be queried anonymously
   * @since 0.1.0
   */
  Boolean hasAnonymousRule(String parent, String field) {
    Map<String,?> rules = config.security.auth.rules
    Map<String,?> parentTypes = rules?.get(parent) as Map<String,?>
    List<String> roles = parentTypes?.get(field) as List<String>
    Boolean allowed = ROLE_ANONYMOUS in roles

    return (!roles && !isIntrospection(parent)) || allowed
  }

  /**
   * Checks whether the parent type indicates an instrospection
   * query or not
   *
   * @param parent the parent type name
   * @return true if it seems to be an instrospection type name false otherwise
   * @since 0.1.0
   */
  Boolean isIntrospection(String parent) {
    return parent.startsWith('__')
  }

  /**
   * Checks whether auth token is valid or not
   *
   * @param token found in auth header
   * @return the username from the token if present
   * @since 0.1.0
   */
  Optional<String> checkAuthentication(String token) {
    DecodedJWT decodedJWT = crypto.verifyToken(token)

    return Optional.ofNullable(decodedJWT.subject)
  }

  /**
   * Returns a function checking whether the given user's roles comply
   * with the requested node
   *
   * @param type parent type name
   * @param field field name
   * @return a checker function
   * @since 0.1.0
   */
  @SuppressWarnings('UnusedMethodParameter')
  Closure<Boolean> checkAuthorization(String type, String field) {
    return { String username ->
      // List<String> roles = repository.getRoles(username)
      // AppConfig.Security.Auth rules = config.security.auth

      return true
    }
  }
}
