package m.tech.baseclean.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import m.tech.baseclean.business.data.network.abstraction.DummyNetworkDataSource
import m.tech.baseclean.framework.datasource.network.api.DummyApi
import m.tech.baseclean.framework.datasource.network.implementation.DummyNetworkImpl
import m.tech.baseclean.framework.datasource.network.mappers.DummyDtoMapper

@InstallIn(ApplicationComponent::class)
@Module
object NetworkImplModule {

    @Provides
    fun provideDummyNetworkDataSource(
        dummyApi: DummyApi,
        dummyDtoMapper: DummyDtoMapper
    ): DummyNetworkDataSource =
        DummyNetworkImpl(
            dummyApi, dummyDtoMapper
        )

}