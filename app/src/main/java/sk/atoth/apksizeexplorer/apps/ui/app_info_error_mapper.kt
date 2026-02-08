package sk.atoth.apksizeexplorer.apps.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import sk.atoth.apksizeexplorer.R
import sk.atoth.apksizeexplorer.apps.data.AppInfoError

@Composable
fun AppInfoError.toComposable(): String = when (this) {
  is AppInfoError.PackageNotFoundError -> stringResource(R.string.error_package_not_found)
  is AppInfoError.PagingLogicError -> stringResource(R.string.error_paging_logic)
}
