package m.tech.baseclean.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import m.tech.baseclean.business.data.cache.abstraction.DummyCacheDataSource
import m.tech.baseclean.framework.datasource.cache.database.dao.DummyDao
import m.tech.baseclean.framework.datasource.cache.implementation.DummyCacheImpl
import m.tech.baseclean.framework.datasource.cache.mappers.DummyCacheMapper

@InstallIn(ApplicationComponent::class)
@Module
object CacheImplModule {

    @Provides
    fun provideDummyCacheDataSource(
        dummyDao: DummyDao,
        dummyCacheMapper: DummyCacheMapper
    ): DummyCacheDataSource = DummyCacheImpl(
        dummyDao,
        dummyCacheMapper
    )


}