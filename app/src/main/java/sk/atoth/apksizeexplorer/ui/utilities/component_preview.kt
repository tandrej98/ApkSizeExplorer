package sk.atoth.apksizeexplorer.ui.utilities

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import sk.atoth.apksizeexplorer.ui.theme.ApkSizeExplorerTheme

@Preview(
  name = "component light",
  showBackground = true,
  uiMode = UI_MODE_NIGHT_NO,
)
@Preview(
  name = "component dark",
  showBackground = true,
  uiMode = UI_MODE_NIGHT_YES,
)
annotation class ComponentPreview

@Composable
fun ComponentPreviewWrapper(
  content: @Composable () -> Unit,
) {
  ApkSizeExplorerTheme {
    Surface(
      color = MaterialTheme.colorScheme.background,
    ) {
      content()
    }
  }
}
