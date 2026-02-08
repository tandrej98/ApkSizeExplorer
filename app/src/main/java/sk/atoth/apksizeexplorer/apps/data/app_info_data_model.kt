package sk.atoth.apksizeexplorer.apps.data

data class AppInfo(
  val label: String,
  val packageName: String,
  val versionName: String?,
  val sizeInBytes: Long,
  val source: AppSource,
)

enum class AppSource {
  SYSTEM,
  PLAY_STORE,
  THIRD_PARTY,
}
