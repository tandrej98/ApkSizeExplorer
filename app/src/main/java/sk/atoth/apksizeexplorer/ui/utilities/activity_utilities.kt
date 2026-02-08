package sk.atoth.apksizeexplorer.ui.utilities

import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ColorScheme
import androidx.compose.ui.graphics.toArgb

fun ComponentActivity.enableCustomEdgeToEdge(
  isDarkModeEnabled: Boolean,
  colorScheme: ColorScheme,
) {
  enableEdgeToEdge(
    statusBarStyle =
    if (isDarkModeEnabled) {
      SystemBarStyle.dark(colorScheme.background.toArgb())
    } else {
      SystemBarStyle.light(
        scrim = colorScheme.background.toArgb(),
        darkScrim = colorScheme.primary.toArgb(),
      )
    },
    navigationBarStyle =
    if (isDarkModeEnabled) {
      SystemBarStyle.dark(colorScheme.background.toArgb())
    } else {
      SystemBarStyle.light(
        scrim = colorScheme.background.toArgb(),
        darkScrim = colorScheme.primary.toArgb(),
      )
    },
  )
}
