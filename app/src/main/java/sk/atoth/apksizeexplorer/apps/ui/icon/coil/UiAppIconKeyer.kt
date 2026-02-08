package sk.atoth.apksizeexplorer.apps.ui.icon.coil

import coil3.key.Keyer
import coil3.request.Options
import sk.atoth.apksizeexplorer.apps.ui.icon.UiAppIcon

private const val APP_ICON_KEY_PREFIX = "appicon"

class UiAppIconKeyer : Keyer<UiAppIcon> {
  override fun key(data: UiAppIcon, options: Options): String = "$APP_ICON_KEY_PREFIX:${data.packageName}"
}
