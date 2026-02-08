package sk.atoth.apksizeexplorer.apps.data

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.paging.PagingSource
import androidx.paging.PagingState
import sk.atoth.apksizeexplorer.core.errors.ErrorWrappingException
import sk.atoth.apksizeexplorer.core.getApkSize
import timber.log.Timber

class InstalledAppsPagingSource(
  private val packageManager: PackageManager,
  private val cachedApps: List<ApplicationInfo> = emptyList(),
  private val enabledSources: Set<AppSource>,
  private val searchQuery: String,
) : PagingSource<Int, AppInfo>() {

  private var filteredApps: List<AppInfo>? = null

  override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AppInfo> {
    return try {
      val pageIndex = params.key ?: 0
      val loadSize = params.loadSize

      val apps: List<AppInfo> = filteredApps ?: cachedApps
        .sortedByDescending { app -> app.getApkSize() }
        .asSequence()
        .map { app -> app.toDataAppInfo(packageManager) }
        .filter { appInfo -> enabledSources.contains(appInfo.source) }
        .filter { appInfo ->
          appInfo.packageName.contains(searchQuery, ignoreCase = true) ||
            appInfo.label.contains(searchQuery, ignoreCase = true)
        }
        .toList()
        .also { filteredApps = it }

      val from = pageIndex * loadSize
      if (from >= apps.size) {
        return LoadResult.Page(
          data = emptyList(),
          prevKey = if (pageIndex == 0) null else pageIndex - 1,
          nextKey = null,
        )
      }

      val to = minOf(from + loadSize, apps.size)
      val page = apps.subList(from, to)

      LoadResult.Page(
        data = page,
        prevKey = if (pageIndex == 0) null else pageIndex - 1,
        nextKey = if (to < apps.size) pageIndex + 1 else null,
      )
    } catch (e: IndexOutOfBoundsException) {
      Timber.e(e, "An invalid index accessed while loading apps.")
      LoadResult.Error(ErrorWrappingException(AppInfoError.PagingLogicError))
    } catch (e: IllegalArgumentException) {
      Timber.e(e, "An illegal argument was provided while loading apps.")
      LoadResult.Error(ErrorWrappingException(AppInfoError.PagingLogicError))
    } catch (e: PackageManager.NameNotFoundException) {
      Timber.e(e, "A requested package was not found while loading apps.")
      LoadResult.Error(ErrorWrappingException(AppInfoError.PackageNotFoundError))
    } catch (t: Throwable) {
      Timber.e(t, "An unexpected error occurred while loading apps.")
      LoadResult.Error(t)
    }
  }

  override fun getRefreshKey(state: PagingState<Int, AppInfo>): Int? = state.anchorPosition?.let { anchorPosition ->
    val anchorPage = state.closestPageToPosition(anchorPosition)
    anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
  }
}
