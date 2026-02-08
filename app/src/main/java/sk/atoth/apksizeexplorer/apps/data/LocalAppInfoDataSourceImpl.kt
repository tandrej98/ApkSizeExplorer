package sk.atoth.apksizeexplorer.apps.data

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.paging.PagingSource
import javax.inject.Inject

class LocalAppInfoDataSourceImpl @Inject constructor(
  private val packageManager: PackageManager,
) : LocalAppInfoDataSource {
  private var appCache: List<ApplicationInfo>? = null
  private val cachedApps
    get() = appCache ?: packageManager.getInstalledApplications(0)
      .also { appCache = it }

  override fun getInstalledAppsPagingSource(
    enabledSources: Set<AppSource>,
    searchQuery: String,
    refreshData: Boolean,
  ): PagingSource<Int, AppInfo> {
    if (refreshData) appCache = null
    return InstalledAppsPagingSource(packageManager, cachedApps, enabledSources, searchQuery)
  }
}
