package m.tech.baseclean.framework.datasource.network.implementation

import m.tech.baseclean.business.data.network.DummyNetworkDataSource
import m.tech.baseclean.framework.datasource.network.api.DummyApi
import m.tech.baseclean.framework.datasource.network.model.response.DummyDto

class DummyNetworkImpl
constructor(
    private val dummyApi: DummyApi
) : DummyNetworkDataSource {

    override suspend fun getDummies(): List<DummyDto> = dummyApi.getDummies().orEmpty()

}