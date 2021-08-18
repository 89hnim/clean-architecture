package m.tech.baseclean.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m.tech.baseclean.business.data.cache.DummyCacheDataSource
import m.tech.baseclean.framework.datasource.cache.database.dao.DummyDao
import m.tech.baseclean.framework.datasource.cache.implementation.DummyCacheImpl

@InstallIn(SingletonComponent::class)
@Module
object CacheImplModule {

    @Provides
    fun provideDummyCacheDataSource(dummyDao: DummyDao): DummyCacheDataSource {
        return DummyCacheImpl(dummyDao)
    }

}