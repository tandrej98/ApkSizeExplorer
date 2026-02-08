package sk.atoth.apksizeexplorer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import sk.atoth.apksizeexplorer.ui.utilities.ComponentPreview
import sk.atoth.apksizeexplorer.ui.utilities.ComponentPreviewWrapper

@Composable
fun LoadingWidget(
  modifier: Modifier = Modifier,
  additionalInfo: String? = null,
) {
  Box(
    modifier = modifier,
    contentAlignment = Alignment.Center,
  ) {
    Column(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      CircularProgressIndicator()
      additionalInfo?.let {
        Text(
          modifier = Modifier.padding(top = 16.dp),
          text = it,
        )
      }
    }
  }
}

@ComponentPreview
@Composable
private fun LoadingWidgetComponentPreview() {
  ComponentPreviewWrapper {
    LoadingWidget(
      additionalInfo = "Loading APK details…",
    )
  }
}
