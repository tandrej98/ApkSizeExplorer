package sk.atoth.apksizeexplorer.apps.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import sk.atoth.apksizeexplorer.apps.data.AppInfoRepository
import sk.atoth.apksizeexplorer.apps.data.AppSource
import javax.inject.Inject

@HiltViewModel
class AppListViewModel @Inject constructor(
  private val appInfoRepository: AppInfoRepository,
) : ViewModel() {

  private val appList: MutableStateFlow<Flow<PagingData<UiAppInfo>>> = MutableStateFlow(flowOf(PagingData.empty()))
  private val filters: MutableStateFlow<Set<AppSource>> = MutableStateFlow(AppSource.entries.toSet())
  private val searchQuery: MutableStateFlow<String> = MutableStateFlow("")
  private val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

  val uiState = observeState()

  @OptIn(ExperimentalCoroutinesApi::class)
  private fun refreshAppList(refreshBackingData: Boolean = false) {
    viewModelScope.launch {
      isLoading.tryEmit(true)
      appInfoRepository.getInstalledAppsPaged(
        enabledSources = filters.value,
        searchQuery = searchQuery.value,
        refreshData = refreshBackingData,
      )
        .mapLatest { it.map { app -> app.toUiAppInfo() } }
        .also {
          appList.tryEmit(it)
          isLoading.tryEmit(false)
        }
    }
  }

  private fun updateFilters(changedFilter: AppSource) {
    filters.update { currentFilters ->
      if (currentFilters.contains(changedFilter)) {
        currentFilters - changedFilter
      } else {
        currentFilters + changedFilter
      }
    }
    refreshAppList()
  }

  private fun updateSearchQuery(query: String) {
    searchQuery.tryEmit(query)
    refreshAppList()
  }

  private fun observeState(): StateFlow<AppListUiState> = combine(appList, filters, isLoading) { appList, filters, isLoading ->
    AppListUiState(
      appsPaged = appList,
      isLoading = isLoading,
      filtersEnabled = filters,
    )
  }.onStart { refreshAppList(true) }
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AppListUiState(isLoading = true))

  fun onEvent(event: AppListUiEvent) {
    when (event) {
      is AppListUiEvent.OnRefresh -> refreshAppList(true)
      is AppListUiEvent.FilterChanged -> updateFilters(event.filter)
      is AppListUiEvent.SearchQueryChanged -> updateSearchQuery(event.query)
    }
  }
}
