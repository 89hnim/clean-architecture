package m.tech.baseclean.framework.datasource.cache.implementation

import m.tech.baseclean.business.data.cache.DummyCacheDataSource
import m.tech.baseclean.framework.datasource.cache.database.dao.DummyDao
import m.tech.baseclean.framework.datasource.cache.model.DummyEntity

class DummyCacheImpl
constructor(
    private val dummyDao: DummyDao
) : DummyCacheDataSource {

    override suspend fun getDummies(): List<DummyEntity> = dummyDao.getDummies()

    override suspend fun getDummyById(id: Int): DummyEntity? = dummyDao.getDummyById(id)

    override suspend fun updateDummy(dummy: DummyEntity) {
        dummyDao.updateDummy(dummy)
    }

    override suspend fun addDummy(dummy: DummyEntity) {
        dummyDao.addDummy(dummy)
    }

    override suspend fun removeDummy(dummy: DummyEntity) {
        dummyDao.removeDummy(dummy)
    }
}
