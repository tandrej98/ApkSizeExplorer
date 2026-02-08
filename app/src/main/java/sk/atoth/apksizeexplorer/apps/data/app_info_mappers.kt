package sk.atoth.apksizeexplorer.apps.data

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import sk.atoth.apksizeexplorer.core.getApkSize
import sk.atoth.apksizeexplorer.core.getAppSource
import sk.atoth.apksizeexplorer.core.getVersionName

fun ApplicationInfo.toDataAppInfo(packageManager: PackageManager) = AppInfo(
  label = loadLabel(packageManager).toString(),
  packageName = packageName,
  versionName = getVersionName(packageManager),
  sizeInBytes = getApkSize(),
  source = getAppSource(packageManager),
)
