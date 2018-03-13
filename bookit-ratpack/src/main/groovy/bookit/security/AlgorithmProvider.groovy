package bookit.security

import javax.inject.Inject
import javax.inject.Provider
import com.auth0.jwt.algorithms.Algorithm
import bookit.config.AppConfig

class AlgorithmProvider implements Provider<Algorithm> {

  @Inject
  AppConfig config

  @Override
  Algorithm get() {
    return Algorithm.HMAC256(config.security.secret)
  }
}
