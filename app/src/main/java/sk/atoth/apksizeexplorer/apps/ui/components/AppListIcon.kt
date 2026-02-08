package sk.atoth.apksizeexplorer.apps.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.SubcomposeAsyncImage
import coil3.request.ImageRequest
import sk.atoth.apksizeexplorer.R
import sk.atoth.apksizeexplorer.apps.ui.icon.UiAppIcon
import sk.atoth.apksizeexplorer.ui.components.LoadingWidget

@Composable
fun AppListIcon(
  modifier: Modifier = Modifier,
  icon: UiAppIcon,
  contentDescription: String,
) {
  val context = LocalContext.current

  SubcomposeAsyncImage(
    modifier = modifier,
    model = ImageRequest.Builder(context)
      .data(icon)
      .build(),
    contentDescription = contentDescription,
    loading = { LoadingWidget() },
    error = {
      Icon(
        painter = painterResource(R.drawable.baseline_broken_image_24),
        contentDescription = contentDescription,
        tint = MaterialTheme.colorScheme.onBackground,
      )
    },
  )
}
