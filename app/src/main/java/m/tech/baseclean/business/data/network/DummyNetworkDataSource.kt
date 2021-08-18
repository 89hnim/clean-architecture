package m.tech.baseclean.business.data.network

import m.tech.baseclean.framework.datasource.network.model.response.DummyDto

interface DummyNetworkDataSource {

    suspend fun getDummies(): List<DummyDto>

}