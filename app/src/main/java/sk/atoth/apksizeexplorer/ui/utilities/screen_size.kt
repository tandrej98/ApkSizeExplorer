package sk.atoth.apksizeexplorer.ui.utilities

import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import sk.atoth.apksizeexplorer.core.findActivity

val LocalWindowSizeClass = compositionLocalOf<WindowSizeClass?> { null }

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun calculateScreenSize(): WindowSizeClass? {
  val context = LocalContext.current
  val activity = remember(context) { context.findActivity() }
  return activity?.let { calculateWindowSizeClass(activity = it) }
}

@Composable
fun ProvideWindowSizeClass(
  content: @Composable () -> Unit,
) {
  val windowSizeClass = calculateScreenSize()
  CompositionLocalProvider(
    LocalWindowSizeClass provides windowSizeClass,
  ) {
    content()
  }
}
