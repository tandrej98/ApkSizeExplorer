package sk.atoth.apksizeexplorer.apps.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import sk.atoth.apksizeexplorer.R
import sk.atoth.apksizeexplorer.apps.data.AppSource
import sk.atoth.apksizeexplorer.apps.ui.toUiAppSourceShort
import sk.atoth.apksizeexplorer.apps.ui.toUiIcon
import sk.atoth.apksizeexplorer.ui.utilities.ComponentPreview
import sk.atoth.apksizeexplorer.ui.utilities.ComponentPreviewWrapper

@Composable
fun AppSourceFilterBar(
  modifier: Modifier = Modifier,
  enabledFilters: Set<AppSource>,
  onChipClicked: (AppSource) -> Unit,
) {
  Surface(
    modifier = modifier,
  ) {
    FlowRow(
      modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp).fillMaxWidth(),
      horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
    ) {
      AppSource.entries.forEach { source ->
        FilterChip(
          selected = enabledFilters.contains(source),
          onClick = { onChipClicked(source) },
          label = { Text(source.toUiAppSourceShort()) },
          colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer,
            selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            selectedTrailingIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
          ),
          leadingIcon = {
            Icon(
              painter = painterResource(source.toUiIcon()),
              contentDescription = stringResource(R.string.app_list_screen_filter_description),
            )
          },
        )
      }
    }
  }
}

@ComponentPreview
@Composable
private fun AppSourceFilterBarPreview() {
  ComponentPreviewWrapper {
    AppSourceFilterBar(
      enabledFilters = setOf(AppSource.PLAY_STORE, AppSource.THIRD_PARTY),
      onChipClicked = {},
    )
  }
}
