package m.tech.baseclean.business.data.cache

import m.tech.baseclean.framework.datasource.cache.model.DummyEntity

interface DummyCacheDataSource {

    suspend fun getDummies(): List<DummyEntity>

    suspend fun getDummyById(id: Int): DummyEntity?

    suspend fun updateDummy(dummy: DummyEntity)

    suspend fun addDummy(dummy: DummyEntity)

    suspend fun removeDummy(dummy: DummyEntity)
}