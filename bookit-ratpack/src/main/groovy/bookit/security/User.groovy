package bookit.security

import groovy.transform.Immutable

@Immutable(copyWith = true)
class User {
  String id
  String username
  String token
  List<String> roles
}
