package m.tech.baseclean.framework.datasource.cache.database.dao

import androidx.room.*
import m.tech.baseclean.framework.datasource.cache.model.DummyEntity

@Dao
interface DummyDao {

    @Insert
    suspend fun addDummy(dummy: DummyEntity)

    @Update
    suspend fun updateDummy(dummy: DummyEntity)

    @Delete
    suspend fun removeDummy(dummy: DummyEntity)

    @Query("SELECT * FROM dummy")
    suspend fun getDummies(): List<DummyEntity>

    @Query("SELECT * FROM dummy WHERE id = :id")
    suspend fun getDummyById(id: Int): DummyEntity?

}
