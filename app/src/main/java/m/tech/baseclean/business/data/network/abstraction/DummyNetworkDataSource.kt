package m.tech.baseclean.business.data.network.abstraction

import m.tech.baseclean.business.domain.Dummy

interface DummyNetworkDataSource {

    suspend fun getDummies(): List<Dummy>

}