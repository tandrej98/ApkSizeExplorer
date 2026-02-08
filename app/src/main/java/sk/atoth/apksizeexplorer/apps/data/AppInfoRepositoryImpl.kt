package sk.atoth.apksizeexplorer.apps.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import sk.atoth.apksizeexplorer.core.di.hilt.IoCoroutineDispatcher
import javax.inject.Inject

class AppInfoRepositoryImpl @Inject constructor(
  private val localAppInfoDataSource: LocalAppInfoDataSource,
  @param:IoCoroutineDispatcher private val ioDispatcher: CoroutineDispatcher,
) : AppInfoRepository {

  override suspend fun getInstalledAppsPaged(
    pageSize: Int,
    enabledSources: Set<AppSource>,
    searchQuery: String,
    refreshData: Boolean,
  ): Flow<PagingData<AppInfo>> = withContext(ioDispatcher) {
    Pager(PagingConfig(pageSize = pageSize)) {
      localAppInfoDataSource.getInstalledAppsPagingSource(enabledSources, searchQuery, refreshData)
    }.flow
  }
}
