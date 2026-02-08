package sk.atoth.apksizeexplorer.apps.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import kotlinx.coroutines.launch
import sk.atoth.apksizeexplorer.R
import sk.atoth.apksizeexplorer.apps.ui.AppListUiState
import sk.atoth.apksizeexplorer.ui.components.PagingLoadState
import sk.atoth.apksizeexplorer.ui.components.ScrollToTopButton
import sk.atoth.apksizeexplorer.ui.utilities.LocalWindowSizeClass
import sk.atoth.apksizeexplorer.ui.utilities.conditional

@Composable
fun AppList(
  modifier: Modifier = Modifier,
  uiState: AppListUiState,
) {
  val apps = uiState.appsPaged.collectAsLazyPagingItems()
  val widthSizeClass = LocalWindowSizeClass.current?.widthSizeClass
  val scrollState = rememberLazyListState()
  val coroutineScope = rememberCoroutineScope()
  var showScrollToTop by remember { mutableStateOf(false) }

  LaunchedEffect(scrollState) {
    snapshotFlow { scrollState.firstVisibleItemIndex }
      .collect {
        showScrollToTop = it > 1
      }
  }

  Box(
    modifier = modifier,
  ) {
    LazyColumn(
      state = scrollState,
      modifier = Modifier
        .fillMaxHeight()
        .conditional(
          condition = widthSizeClass == WindowWidthSizeClass.Compact,
          onTrue = { Modifier.fillMaxWidth() },
          onElse = { Modifier.size(500.dp) },
        ),
    ) {
      if (apps.itemCount == 0) {
        item {
          PagingLoadState(
            modifier = Modifier.fillParentMaxSize(),
            loadState = apps.loadState.refresh,
          ) { modifier ->
            Box(
              modifier = modifier,
              contentAlignment = Alignment.Center,
            ) {
              Text(
                text = stringResource(R.string.app_list_screen_no_apps_installed),
              )
            }
          }
        }
      } else {
        item {
          PagingLoadState(modifier = Modifier.fillMaxWidth(), loadState = apps.loadState.prepend)
        }
        items(
          count = apps.itemCount,
          key = apps.itemKey { it.packageName },
          contentType = apps.itemContentType { it },
        ) { index ->
          Row(modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp)) {
            apps[index]?.also { app ->
              AppListIcon(
                modifier = Modifier.size(50.dp),
                icon = app.icon,
                contentDescription = stringResource(
                  R.string.app_list_screen_icon_description,
                  app.label,
                ),
              )
              AppInformation(
                modifier = Modifier.padding(start = 16.dp),
                app = app,
              )
            }
          }
          Spacer(modifier = Modifier.size(20.dp))
        }
        item {
          PagingLoadState(modifier = Modifier.fillMaxWidth(), loadState = apps.loadState.append)
        }
        item {
          Spacer(modifier = Modifier.size(50.dp))
        }
      }
    }
    ScrollToTopButton(
      modifier = Modifier
        .align(Alignment.BottomEnd)
        .padding(16.dp),
      isVisible = showScrollToTop,
      onClick = {
        coroutineScope.launch {
          scrollState.animateScrollToItem(0)
        }
      },
    )
  }
}
