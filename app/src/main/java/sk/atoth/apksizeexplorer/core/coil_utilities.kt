package sk.atoth.apksizeexplorer.core

import android.app.Application
import coil3.ImageLoader
import coil3.memory.MemoryCache
import sk.atoth.apksizeexplorer.apps.ui.icon.coil.UiAppIconFetcher
import sk.atoth.apksizeexplorer.apps.ui.icon.coil.UiAppIconKeyer

fun Application.createApkSizeExplorerImageLoader() = ImageLoader.Builder(this)
  .memoryCache(
    MemoryCache.Builder().maxSizePercent(this).build(),
  )
  .components {
    add(UiAppIconKeyer())
    add(UiAppIconFetcher.Factory(this@createApkSizeExplorerImageLoader))
  }
  .build()
