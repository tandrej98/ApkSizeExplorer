package sk.atoth.apksizeexplorer.apps.data

import sk.atoth.apksizeexplorer.core.errors.BaseError

sealed class AppInfoError : BaseError {
  object PackageNotFoundError : AppInfoError()
  object PagingLogicError : AppInfoError()
}
