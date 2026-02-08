package sk.atoth.apksizeexplorer

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import dagger.hilt.android.HiltAndroidApp
import sk.atoth.apksizeexplorer.core.createApkSizeExplorerImageLoader
import timber.log.Timber

@HiltAndroidApp
class ApkExplorerApplication :
  Application(),
  SingletonImageLoader.Factory {
  override fun newImageLoader(context: PlatformContext): ImageLoader = createApkSizeExplorerImageLoader()

  override fun onCreate() {
    super.onCreate()

    if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
  }
}
