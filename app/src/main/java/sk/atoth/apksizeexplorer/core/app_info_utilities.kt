package sk.atoth.apksizeexplorer.core

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import sk.atoth.apksizeexplorer.apps.data.AppSource
import java.io.File
import kotlin.collections.contains

private const val PLAYSTORE_INSTALLER = "com.android.vending"
private const val PLAYSTORE_INSTALLER_LEGACY = "com.google.android.feedback"

private val PLAYSTORE_INSTALLERS = setOf(
  PLAYSTORE_INSTALLER,
  PLAYSTORE_INSTALLER_LEGACY,
)

fun ApplicationInfo.isSystemApp(): Boolean = (flags and ApplicationInfo.FLAG_SYSTEM) != 0

fun ApplicationInfo.isUpdatedSystemApp(): Boolean = (flags and ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0

fun ApplicationInfo.getInstallerPackage(packageManager: PackageManager) = if (android.os.Build.VERSION.SDK_INT >= 30) {
  packageManager.getInstallSourceInfo(packageName).initiatingPackageName
} else {
  @Suppress("DEPRECATION")
  packageManager.getInstallerPackageName(packageName)
}

fun ApplicationInfo.getApkSize(): Long = File(sourceDir).length()

fun ApplicationInfo.getVersionName(pm: PackageManager): String? = pm.getPackageInfo(packageName, 0).versionName

fun ApplicationInfo.getAppSource(packageManager: PackageManager): AppSource = when {
  isSystemApp() || isUpdatedSystemApp() -> AppSource.SYSTEM
  PLAYSTORE_INSTALLERS.contains(getInstallerPackage(packageManager)) -> AppSource.PLAY_STORE
  else -> AppSource.THIRD_PARTY
}
