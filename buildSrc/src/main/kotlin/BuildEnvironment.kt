object BuildEnvironment {
  private const val KEYSTORE_FILE_KEY = "KEYSTORE_FILE"
  private const val KEYSTORE_PASSWORD_KEY = "KEYSTORE_PASSWORD"
  private const val KEY_ALIAS_KEY = "KEY_ALIAS"
  private const val KEY_PASSWORD_KEY = "KEY_PASSWORD"
  private const val SIGNING_ENABLED_KEY = "SIGNING_ENABLED"

  val keystoreFile: String?
    get() = System.getenv(KEYSTORE_FILE_KEY)

  val keystorePassword: String?
    get() = System.getenv(KEYSTORE_PASSWORD_KEY)

  val keyAlias: String?
    get() = System.getenv(KEY_ALIAS_KEY)

  val keyPassword: String?
    get() = System.getenv(KEY_PASSWORD_KEY)

  val isSigningEnabled: Boolean
    get() = System.getenv(SIGNING_ENABLED_KEY)?.toBoolean() == true
}
