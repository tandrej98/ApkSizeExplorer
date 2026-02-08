package sk.atoth.apksizeexplorer.core.errors

class ErrorWrappingException(
  val error: BaseError,
  override val message: String? = null,
  override val cause: Throwable? = null,
) : RuntimeException(message, cause)
