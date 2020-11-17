package m.tech.baseclean.framework.datasource.cache.implementation

import m.tech.baseclean.business.data.cache.abstraction.DummyCacheDataSource
import m.tech.baseclean.business.domain.Dummy
import m.tech.baseclean.framework.datasource.cache.database.dao.DummyDao
import m.tech.baseclean.framework.datasource.cache.mappers.DummyCacheMapper

class DummyCacheImpl
constructor(
    private val dummyDao: DummyDao,
    private val dummyMapper: DummyCacheMapper
) : DummyCacheDataSource {

    override suspend fun getDummies(): List<Dummy> =
        dummyMapper.fromEntities(dummyDao.getDummies())

    override suspend fun getDummyById(id: Int): Dummy? =
        dummyDao.getDummyById(id)?.let {
            dummyMapper.fromEntity(it)
        }

    override suspend fun updateDummy(dummy: Dummy) {
        dummyDao.updateDummy(dummyMapper.toEntity(dummy))
    }

    override suspend fun addDummy(dummy: Dummy) {
        dummyDao.addDummy(dummyMapper.toEntity(dummy))
    }

    override suspend fun removeDummy(dummy: Dummy) {
        dummyDao.removeDummy(dummyMapper.toEntity(dummy))
    }
}
