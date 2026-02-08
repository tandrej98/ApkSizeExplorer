package sk.atoth.apksizeexplorer.apps.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import sk.atoth.apksizeexplorer.R
import sk.atoth.apksizeexplorer.apps.data.AppInfo
import sk.atoth.apksizeexplorer.apps.data.AppSource
import sk.atoth.apksizeexplorer.apps.ui.icon.UiAppIcon

fun AppInfo.toUiAppInfo() = UiAppInfo(
  label = label,
  packageName = packageName,
  versionName = versionName,
  sizeInBytes = sizeInBytes,
  icon = UiAppIcon(packageName),
)

@Composable
fun AppSource.toUiAppSourceShort() = when (this) {
  AppSource.SYSTEM -> R.string.app_source_system_short
  AppSource.PLAY_STORE -> R.string.app_source_play_store_short
  AppSource.THIRD_PARTY -> R.string.app_source_third_party_short
}.let { stringResource(it) }

@Composable
fun AppSource.toUiIcon() = when (this) {
  AppSource.SYSTEM -> R.drawable.outline_android_24
  AppSource.PLAY_STORE -> R.drawable.outline_play_arrow_24
  AppSource.THIRD_PARTY -> R.drawable.outline_extension_24
}
