package sk.atoth.apksizeexplorer.apps.ui

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import sk.atoth.apksizeexplorer.apps.data.AppSource
import sk.atoth.apksizeexplorer.apps.ui.icon.UiAppIcon

data class UiAppInfo(
  val label: String,
  val packageName: String,
  val versionName: String?,
  val sizeInBytes: Long,
  val icon: UiAppIcon,
)

data class AppListUiState(
  val appsPaged: Flow<PagingData<UiAppInfo>> = flowOf(PagingData.empty()),
  val isLoading: Boolean = false,
  val filtersEnabled: Set<AppSource> = AppSource.entries.toSet(),
)

sealed interface AppListUiEvent {
  data object OnRefresh : AppListUiEvent
  data class FilterChanged(val filter: AppSource) : AppListUiEvent
  data class SearchQueryChanged(val query: String) : AppListUiEvent
}
