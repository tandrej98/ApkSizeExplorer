package sk.atoth.apksizeexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import dagger.hilt.android.AndroidEntryPoint
import sk.atoth.apksizeexplorer.apps.ui.AppListScreen
import sk.atoth.apksizeexplorer.ui.theme.ApkSizeExplorerTheme
import sk.atoth.apksizeexplorer.ui.utilities.ProvideWindowSizeClass
import sk.atoth.apksizeexplorer.ui.utilities.enableCustomEdgeToEdge

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ApkSizeExplorerTheme {
        enableCustomEdgeToEdge(
          isDarkModeEnabled = isSystemInDarkTheme(),
          colorScheme = MaterialTheme.colorScheme,
        )

        ProvideWindowSizeClass {
          AppListScreen()
        }
      }
    }
  }
}
