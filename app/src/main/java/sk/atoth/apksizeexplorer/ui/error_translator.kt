package sk.atoth.apksizeexplorer.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import sk.atoth.apksizeexplorer.R
import sk.atoth.apksizeexplorer.apps.data.AppInfoError
import sk.atoth.apksizeexplorer.apps.ui.toComposable
import sk.atoth.apksizeexplorer.core.errors.BaseError
import sk.atoth.apksizeexplorer.core.errors.UnknownError

@Composable
fun BaseError.toUiMessage(): String = when (this) {
  is UnknownError -> stringResource(R.string.error_unknown_error)
  is AppInfoError -> toComposable()
  else -> stringResource(R.string.error_unknown_error)
}
