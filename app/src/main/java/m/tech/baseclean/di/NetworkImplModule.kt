package m.tech.baseclean.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import m.tech.baseclean.business.data.network.DummyNetworkDataSource
import m.tech.baseclean.framework.datasource.network.api.DummyApi
import m.tech.baseclean.framework.datasource.network.implementation.DummyNetworkImpl

@InstallIn(SingletonComponent::class)
@Module
object NetworkImplModule {

    @Provides
    fun provideDummyNetworkDataSource(dummyApi: DummyApi): DummyNetworkDataSource {
        return DummyNetworkImpl(dummyApi)
    }

}