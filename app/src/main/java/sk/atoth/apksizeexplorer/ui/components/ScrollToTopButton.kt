package sk.atoth.apksizeexplorer.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import sk.atoth.apksizeexplorer.R
import sk.atoth.apksizeexplorer.ui.utilities.ComponentPreview
import sk.atoth.apksizeexplorer.ui.utilities.ComponentPreviewWrapper

@Composable
fun ScrollToTopButton(
  modifier: Modifier = Modifier,
  isVisible: Boolean,
  onClick: () -> Unit,
) {
  AnimatedVisibility(
    visible = isVisible,
    modifier = modifier,
  ) {
    IconButton(
      onClick = onClick,
      colors = IconButtonDefaults.iconButtonColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
    ) {
      Icon(
        painter = painterResource(R.drawable.outline_arrow_upward_24),
        contentDescription = stringResource(R.string.scroll_to_top_button_description),
      )
    }
  }
}

@ComponentPreview
@Composable
private fun ScrollToTopButtonPreview() {
  ComponentPreviewWrapper {
    ScrollToTopButton(
      isVisible = true,
      onClick = { },
    )
  }
}
