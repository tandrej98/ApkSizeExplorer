package sk.atoth.apksizeexplorer.core.di.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sk.atoth.apksizeexplorer.apps.data.AppInfoRepository
import sk.atoth.apksizeexplorer.apps.data.AppInfoRepositoryImpl
import sk.atoth.apksizeexplorer.apps.data.LocalAppInfoDataSource
import sk.atoth.apksizeexplorer.apps.data.LocalAppInfoDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class AppInfoModule {
  @Binds
  abstract fun bindAppInfoRepository(
    appInfoRepositoryImpl: AppInfoRepositoryImpl,
  ): AppInfoRepository

  @Binds
  abstract fun bindLocalAppInfoDataSource(
    localAppInfoDataSourceImpl: LocalAppInfoDataSourceImpl,
  ): LocalAppInfoDataSource
}
