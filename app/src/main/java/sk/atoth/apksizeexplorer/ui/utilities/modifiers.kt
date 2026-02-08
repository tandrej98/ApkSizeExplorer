package sk.atoth.apksizeexplorer.ui.utilities

import androidx.compose.ui.Modifier

fun Modifier.conditional(
  condition: Boolean,
  onTrue: Modifier.() -> Modifier,
  onElse: Modifier.() -> Modifier = { this },
): Modifier = if (condition) {
  then(onTrue())
} else {
  then(onElse())
}
