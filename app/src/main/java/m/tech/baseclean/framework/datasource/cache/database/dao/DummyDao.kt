package m.tech.baseclean.framework.datasource.cache.database.dao

import androidx.room.*
import m.tech.baseclean.framework.datasource.cache.model.DummyCacheEntity
import m.tech.baseclean.framework.datasource.cache.model.DummyCacheEntity.Companion.ID
import m.tech.baseclean.framework.datasource.cache.model.DummyCacheEntity.Companion.TABLE_NAME

@Dao
interface DummyDao {

    @Insert
    suspend fun addDummy(dummy: DummyCacheEntity)

    @Update
    suspend fun updateDummy(dummy: DummyCacheEntity)

    @Delete
    suspend fun removeDummy(dummy: DummyCacheEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getDummies(): List<DummyCacheEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE $ID = :id")
    suspend fun getDummyById(id: Int): DummyCacheEntity?

}
