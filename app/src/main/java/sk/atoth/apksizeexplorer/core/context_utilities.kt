package sk.atoth.apksizeexplorer.core

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.ContextWrapper
import androidx.core.content.getSystemService

fun Context.copyValue(
  label: String,
  value: String,
) {
  getSystemService<ClipboardManager>()?.also { clipboardManager ->
    clipboardManager.setPrimaryClip(
      ClipData.newPlainText(
        label,
        value,
      ),
    )
  }
}

fun Context.findActivity(): Activity? = when (this) {
  is Activity -> this
  is ContextWrapper -> baseContext.findActivity()
  else -> null
}
