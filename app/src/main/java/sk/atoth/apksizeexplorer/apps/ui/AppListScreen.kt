package sk.atoth.apksizeexplorer.apps.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.LoadStates
import androidx.paging.PagingData
import kotlinx.coroutines.flow.flowOf
import sk.atoth.apksizeexplorer.R
import sk.atoth.apksizeexplorer.apps.data.AppSource
import sk.atoth.apksizeexplorer.apps.ui.components.AppList
import sk.atoth.apksizeexplorer.apps.ui.components.AppSourceFilterBar
import sk.atoth.apksizeexplorer.apps.ui.icon.UiAppIcon
import sk.atoth.apksizeexplorer.ui.BaseScreen
import sk.atoth.apksizeexplorer.ui.components.SearchBar
import sk.atoth.apksizeexplorer.ui.theme.ApkSizeExplorerTheme
import sk.atoth.apksizeexplorer.ui.utilities.DevicePreview
import sk.atoth.apksizeexplorer.ui.utilities.LocalWindowSizeClass
import sk.atoth.apksizeexplorer.ui.utilities.conditional

@Composable
fun AppListScreen(
  viewModel: AppListViewModel = hiltViewModel(),
) {
  val state by viewModel.uiState.collectAsStateWithLifecycle()

  AppListScreen(
    uiState = state,
    onEventHandler = { event -> viewModel.onEvent(event) },
  )
}

@Composable
fun AppListScreen(
  uiState: AppListUiState,
  onEventHandler: (AppListUiEvent) -> Unit,
) {
  val widthSizeClass = LocalWindowSizeClass.current?.widthSizeClass

  BaseScreen(
    title = stringResource(R.string.app_list_screen_title),
  ) { modifier ->
    PullToRefreshBox(
      modifier = modifier,
      contentAlignment = Alignment.Center,
      isRefreshing = uiState.isLoading,
      onRefresh = { onEventHandler(AppListUiEvent.OnRefresh) },
    ) {
      Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        SearchBar(
          modifier = Modifier
            .conditional(
              condition = widthSizeClass == WindowWidthSizeClass.Compact,
              onTrue = { Modifier.fillMaxWidth() },
              onElse = { Modifier.sizeIn(maxWidth = 500.dp).align(Alignment.CenterHorizontally) },
            ).padding(horizontal = 16.dp, vertical = 4.dp),
          onSearch = { query -> onEventHandler(AppListUiEvent.SearchQueryChanged(query)) },
        )
        AppSourceFilterBar(
          modifier = Modifier.fillMaxWidth(),
          enabledFilters = uiState.filtersEnabled,
          onChipClicked = { source -> onEventHandler(AppListUiEvent.FilterChanged(source)) },
        )
        AppList(
          modifier = Modifier.weight(1.0f),
          uiState = uiState,
        )
      }
    }
  }
}

private val emptyPagingData = PagingData.empty<UiAppInfo>(
  sourceLoadStates = LoadStates(
    refresh = LoadState.NotLoading(false),
    prepend = LoadState.NotLoading(false),
    append = LoadState.NotLoading(false),
  ),
)

private val appPagingData = PagingData.from(
  listOf(
    UiAppInfo(
      label = "Example App",
      packageName = "com.example.app",
      versionName = "1.0.0",
      sizeInBytes = 15_000_000,
      icon = UiAppIcon("com.example.app"),
    ),
    UiAppInfo(
      label = "System App",
      packageName = "com.android.systemapp",
      versionName = null,
      sizeInBytes = 50_000_000,
      icon = UiAppIcon("com.example.app"),
    ),
  ),
  sourceLoadStates = LoadStates(
    refresh = LoadState.NotLoading(false),
    prepend = LoadState.NotLoading(false),
    append = LoadState.NotLoading(false),
  ),
)

@DevicePreview
@Composable
fun AppListScreenEmptyPreview() {
  ApkSizeExplorerTheme {
    AppListScreen(
      uiState = AppListUiState(
        appsPaged = flowOf(emptyPagingData),
        isLoading = false,
        filtersEnabled = setOf(AppSource.SYSTEM, AppSource.PLAY_STORE),
      ),
      onEventHandler = {},
    )
  }
}

@DevicePreview
@Composable
fun AppListScreenDataPreview() {
  ApkSizeExplorerTheme {
    AppListScreen(
      uiState = AppListUiState(
        appsPaged = flowOf(appPagingData),
        isLoading = true,
        filtersEnabled = setOf(AppSource.SYSTEM, AppSource.PLAY_STORE),
      ),
      onEventHandler = {},
    )
  }
}
