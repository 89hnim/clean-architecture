package m.tech.baseclean.framework.datasource.network.implementation

import m.tech.baseclean.business.data.network.abstraction.DummyNetworkDataSource
import m.tech.baseclean.business.domain.Dummy
import m.tech.baseclean.framework.datasource.network.api.DummyApi
import m.tech.baseclean.framework.datasource.network.mappers.DummyNetworkMapper

class DummyNetworkImpl
constructor(
    private val dummyApi: DummyApi,
    private val dummyNetworkMapper: DummyNetworkMapper
) : DummyNetworkDataSource {

    override suspend fun getDummies(): List<Dummy> =
        dummyNetworkMapper.toDomainList(dummyApi.getDummies())

}