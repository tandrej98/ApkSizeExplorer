package sk.atoth.apksizeexplorer.apps.data

import androidx.paging.PagingSource

interface LocalAppInfoDataSource {
  fun getInstalledAppsPagingSource(
    enabledSources: Set<AppSource>,
    searchQuery: String,
    refreshData: Boolean,
  ): PagingSource<Int, AppInfo>
}
