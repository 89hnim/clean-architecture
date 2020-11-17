package m.tech.baseclean.business.data.cache.abstraction

import m.tech.baseclean.business.domain.Dummy

interface DummyCacheDataSource {

    suspend fun getDummies(): List<Dummy>

    suspend fun getDummyById(id: Int): Dummy?

    suspend fun updateDummy(dummy: Dummy)

    suspend fun addDummy(dummy: Dummy)

    suspend fun removeDummy(dummy: Dummy)
}