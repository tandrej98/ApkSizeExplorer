package sk.atoth.apksizeexplorer.ui.utilities

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(
  name = "phone light",
  showBackground = true,
  device = Devices.PHONE,
  uiMode = UI_MODE_NIGHT_NO,
  showSystemUi = true,
)
@Preview(
  name = "phone dark",
  showBackground = true,
  device = Devices.PHONE,
  uiMode = UI_MODE_NIGHT_YES,
  showSystemUi = true,
)
annotation class DevicePreviewPhone

@Preview(
  name = "tablet light",
  device = Devices.TABLET,
  uiMode = UI_MODE_NIGHT_NO,
  showBackground = true,
  showSystemUi = true,
)
@Preview(
  name = "tablet dark",
  device = Devices.TABLET,
  uiMode = UI_MODE_NIGHT_YES,
  showBackground = true,
  showSystemUi = true,
)
annotation class DevicePreviewTablet

@DevicePreviewPhone
@DevicePreviewTablet
annotation class DevicePreview
