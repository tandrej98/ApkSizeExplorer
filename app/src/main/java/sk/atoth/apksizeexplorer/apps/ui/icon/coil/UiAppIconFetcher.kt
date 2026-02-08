package sk.atoth.apksizeexplorer.apps.ui.icon.coil

import android.content.Context
import android.content.pm.PackageManager
import coil3.ImageLoader
import coil3.asImage
import coil3.decode.DataSource
import coil3.fetch.FetchResult
import coil3.fetch.Fetcher
import coil3.fetch.ImageFetchResult
import coil3.request.Options
import sk.atoth.apksizeexplorer.apps.ui.icon.UiAppIcon

class UiAppIconFetcher(
  private val packageManager: PackageManager,
  private val data: UiAppIcon,
) : Fetcher {

  override suspend fun fetch(): FetchResult? = packageManager
    .let { pm -> runCatching { pm.getApplicationIcon(data.packageName) }.getOrNull() }
    ?.let { drawable ->
      ImageFetchResult(
        image = drawable.asImage(),
        isSampled = false,
        dataSource = DataSource.DISK,
      )
    }

  class Factory(
    private val context: Context,
  ) : Fetcher.Factory<UiAppIcon> {
    override fun create(data: UiAppIcon, options: Options, imageLoader: ImageLoader): Fetcher = UiAppIconFetcher(context.packageManager, data)
  }
}
