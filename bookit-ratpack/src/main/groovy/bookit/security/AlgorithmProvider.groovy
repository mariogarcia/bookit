package bookit.security

import javax.inject.Inject
import javax.inject.Provider
import com.auth0.jwt.algorithms.Algorithm
import bookit.config.AppConfig

/**
 * Configures the instance of {@link Algorithm} used though all the
 * application
 *
 * @since 0.1.0
 */
class AlgorithmProvider implements Provider<Algorithm> {

  /**
   * Access to app's configuration
   *
   * @since 0.1.0
   */
  @Inject
  AppConfig config

  @Override
  Algorithm get() {
    return Algorithm.HMAC256(config.security.secret)
  }
}
