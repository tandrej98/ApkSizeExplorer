package sk.atoth.apksizeexplorer.apps.ui.components

import android.text.format.Formatter.formatFileSize
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import sk.atoth.apksizeexplorer.R
import sk.atoth.apksizeexplorer.apps.ui.UiAppInfo
import sk.atoth.apksizeexplorer.apps.ui.icon.UiAppIcon
import sk.atoth.apksizeexplorer.core.copyValue
import sk.atoth.apksizeexplorer.ui.utilities.ComponentPreview
import sk.atoth.apksizeexplorer.ui.utilities.ComponentPreviewWrapper

@Composable
fun AppInformation(
  modifier: Modifier = Modifier,
  app: UiAppInfo,
) {
  val context = LocalContext.current

  Column(modifier = modifier) {
    AppLabel(app.label)
    AppInformationLine(
      type = stringResource(R.string.app_list_element_label_package),
      value = app.packageName,
    )
    AppInformationLine(
      type = stringResource(R.string.app_list_element_label_size),
      value = formatFileSize(context, app.sizeInBytes),
    )
    AppInformationLine(
      type = stringResource(R.string.app_list_element_label_version),
      value = app.versionName ?: stringResource(R.string.unknown_version),
    )
  }
}

@Composable
private fun AppLabel(
  text: String,
  modifier: Modifier = Modifier,
) {
  Text(
    modifier = modifier,
    text = text,
    style = MaterialTheme.typography.headlineMedium,
  )
}

@Composable
private fun AppInformationLine(
  type: String,
  value: String,
) {
  var isExpanded by remember { mutableStateOf(false) }
  val context = LocalContext.current
  Row(
    Modifier.combinedClickable(
      onClick = { isExpanded = !isExpanded },
      onLongClick = { context.copyValue(label = type, value = value) },
      onClickLabel = stringResource(R.string.app_list_element_on_click_label, type),
      onLongClickLabel = stringResource(R.string.app_list_element_on_long_click_label, type),
    ),
  ) {
    Text(
      text = "$type:",
      style = MaterialTheme.typography.bodyLarge,
      modifier = Modifier.padding(end = 4.dp),
    )
    Text(
      text = value,
      style = MaterialTheme.typography.bodyLarge,
      fontWeight = FontWeight.Bold,
      maxLines = if (isExpanded) Int.MAX_VALUE else 1,
      overflow = TextOverflow.Ellipsis,
    )
  }
}

@ComponentPreview
@Composable
private fun AppInformationPreview() {
  ComponentPreviewWrapper {
    AppInformation(
      app = UiAppInfo(
        label = "Example app ",
        packageName = "com.example.app",
        versionName = "1.0.0",
        sizeInBytes = 123456789,
        icon = UiAppIcon("com.example.app"),
      ),
    )
  }
}
