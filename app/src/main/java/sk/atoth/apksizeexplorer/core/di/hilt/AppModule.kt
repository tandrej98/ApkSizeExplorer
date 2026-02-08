package sk.atoth.apksizeexplorer.core.di.hilt

import android.content.Context
import android.content.pm.PackageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  @Provides
  fun providePackageManager(@ApplicationContext context: Context): PackageManager = context.packageManager

  @IoCoroutineDispatcher
  @Provides
  fun provideCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO
}
