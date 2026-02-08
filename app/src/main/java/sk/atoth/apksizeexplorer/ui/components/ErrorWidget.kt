package sk.atoth.apksizeexplorer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import sk.atoth.apksizeexplorer.R
import sk.atoth.apksizeexplorer.core.errors.BaseError
import sk.atoth.apksizeexplorer.core.errors.UnknownError
import sk.atoth.apksizeexplorer.ui.toUiMessage
import sk.atoth.apksizeexplorer.ui.utilities.ComponentPreview
import sk.atoth.apksizeexplorer.ui.utilities.ComponentPreviewWrapper

@Composable
fun ErrorWidget(
  modifier: Modifier = Modifier,
  error: BaseError,
) {
  Box(
    modifier = modifier,
    contentAlignment = Alignment.Center,
  ) {
    Column(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
    ) {
      Row(modifier = Modifier.padding(vertical = 8.dp)) {
        Icon(
          modifier = Modifier.padding(end = 8.dp),
          imageVector = ImageVector.vectorResource(R.drawable.baseline_error_outline_24),
          tint = MaterialTheme.colorScheme.error,
          contentDescription = stringResource(R.string.error_widget_description),
        )
        Text(
          text = stringResource(R.string.error_widget_heading),
          style = MaterialTheme.typography.bodyLarge,
          color = MaterialTheme.colorScheme.error,
        )
      }
      Text(
        text = error.toUiMessage(),
        style = MaterialTheme.typography.bodyMedium,
        color = MaterialTheme.colorScheme.error,
      )
    }
  }
}

@ComponentPreview
@Composable
private fun ErrorWidgetPreview() {
  ComponentPreviewWrapper {
    ErrorWidget(
      error = UnknownError(),
    )
  }
}
