package sk.atoth.apksizeexplorer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import sk.atoth.apksizeexplorer.R
import sk.atoth.apksizeexplorer.ui.utilities.ComponentPreview
import sk.atoth.apksizeexplorer.ui.utilities.ComponentPreviewWrapper

@Composable
fun SearchBar(
  modifier: Modifier = Modifier,
  onSearch: (String) -> Unit,
) {
  val state = rememberSaveable(saver = TextFieldState.Saver) { TextFieldState() }
  Surface(
    modifier = modifier,
  ) {
    Row(
      modifier = Modifier.height(IntrinsicSize.Min),
      horizontalArrangement = Arrangement.Center,
    ) {
      OutlinedTextField(
        modifier = Modifier
          .weight(1f)
          .fillMaxHeight(),
        placeholder = { Text(stringResource(R.string.search_component_hint)) },
        state = state,
        lineLimits = TextFieldLineLimits.SingleLine,
        trailingIcon = {
          if (state.text.isNotEmpty()) {
            IconButton(
              onClick = {
                state.clearText()
                onSearch("")
              },
            ) {
              Icon(
                painter = painterResource(R.drawable.outline_close_24),
                contentDescription = stringResource(R.string.search_component_clear_icon_description),
              )
            }
          }
        },
      )
      IconButton(
        modifier = Modifier.fillMaxHeight(),
        onClick = { onSearch(state.text.toString()) },
        colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
      ) {
        Icon(
          painter = painterResource(R.drawable.baseline_search_24),
          contentDescription = stringResource(R.string.search_component_icon_description),
        )
      }
    }
  }
}

@ComponentPreview
@Composable
private fun SearchBarComponentPreview() {
  ComponentPreviewWrapper {
    SearchBar(
      onSearch = {},
    )
  }
}
