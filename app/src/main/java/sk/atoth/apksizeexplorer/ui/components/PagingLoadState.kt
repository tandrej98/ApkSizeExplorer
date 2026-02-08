package sk.atoth.apksizeexplorer.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import sk.atoth.apksizeexplorer.core.errors.ErrorWrappingException
import sk.atoth.apksizeexplorer.core.errors.UnknownError
import sk.atoth.apksizeexplorer.ui.utilities.ComponentPreview
import sk.atoth.apksizeexplorer.ui.utilities.ComponentPreviewWrapper

@Composable
fun PagingLoadState(
  modifier: Modifier = Modifier,
  loadState: LoadState,
  notLoadingContent: @Composable (Modifier) -> Unit = { },
) {
  when (loadState) {
    is LoadState.Error -> ErrorWidget(
      modifier = modifier,
      error = (loadState.error as? ErrorWrappingException)?.error ?: UnknownError(),
    )

    LoadState.Loading -> LoadingWidget(modifier = modifier)

    is LoadState.NotLoading -> notLoadingContent(modifier)
  }
}

@ComponentPreview
@Composable
private fun PagingLoadStateLoadingPreview() {
  ComponentPreviewWrapper {
    PagingLoadState(loadState = LoadState.Loading)
  }
}

@ComponentPreview
@Composable
private fun PagingLoadStateErrorPreview() {
  ComponentPreviewWrapper {
    PagingLoadState(loadState = LoadState.Error(ErrorWrappingException(UnknownError())))
  }
}

@ComponentPreview
@Composable
private fun PagingLoadStateEmptyPreview() {
  ComponentPreviewWrapper {
    PagingLoadState(loadState = LoadState.NotLoading(true)) {
      Text("No items to load")
    }
  }
}
