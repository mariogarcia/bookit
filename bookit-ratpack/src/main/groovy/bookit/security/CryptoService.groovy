package bookit.security

import javax.inject.Inject
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

class CryptoService {

  @Inject
  AlgorithmProvider algorithmProvider

  String createToken(String issuer) {
    Algorithm algorithm = algorithmProvider.get()

    return JWT
      .create()
      .withIssuer(issuer)
      .sign(algorithm)
  }

  String hash(String text) {
    return MessageDigest
      .getInstance("SHA-256")
      .digest(text.getBytes(StandardCharsets.UTF_8))
      .encodeHex()
      .toString()
  }
}
