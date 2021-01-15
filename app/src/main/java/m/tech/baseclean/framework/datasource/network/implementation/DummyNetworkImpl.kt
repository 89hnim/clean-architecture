package m.tech.baseclean.framework.datasource.network.implementation

import m.tech.baseclean.business.data.network.abstraction.DummyNetworkDataSource
import m.tech.baseclean.business.domain.Dummy
import m.tech.baseclean.framework.datasource.network.api.DummyApi
import m.tech.baseclean.framework.datasource.network.mappers.DummyDtoMapper

class DummyNetworkImpl
constructor(
    private val dummyApi: DummyApi,
    private val dummyDtoMapper: DummyDtoMapper
) : DummyNetworkDataSource {

    override suspend fun getDummies(): List<Dummy> =
        dummyDtoMapper.toDomainList(dummyApi.getDummies())

}