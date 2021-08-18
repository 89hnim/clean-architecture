package m.tech.baseclean.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m.tech.baseclean.business.data.cache.DummyCacheDataSource
import m.tech.baseclean.business.data.network.DummyNetworkDataSource
import m.tech.baseclean.business.data.repository.DummyRepository
import m.tech.baseclean.framework.datasource.repository.DummyRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    fun provideDummyRepository(
        dummyCacheDataSource: DummyCacheDataSource,
        dummyNetworkDataSource: DummyNetworkDataSource,
        appDispatchers: AppDispatchers
    ): DummyRepository {
        return DummyRepositoryImpl(
            dummyCacheDataSource = dummyCacheDataSource,
            dummyNetworkDataSource = dummyNetworkDataSource,
            appDispatchers = appDispatchers
        )
    }

}