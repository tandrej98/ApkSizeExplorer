package sk.atoth.apksizeexplorer.apps.data

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

private const val PAGE_SIZE = 100

interface AppInfoRepository {
  suspend fun getInstalledAppsPaged(
    pageSize: Int = PAGE_SIZE,
    enabledSources: Set<AppSource> = AppSource.entries.toSet(),
    searchQuery: String = "",
    refreshData: Boolean = false,
  ): Flow<PagingData<AppInfo>>
}
