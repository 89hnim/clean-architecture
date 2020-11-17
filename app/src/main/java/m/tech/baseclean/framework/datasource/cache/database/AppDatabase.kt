package m.tech.baseclean.framework.datasource.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import m.tech.baseclean.framework.datasource.cache.database.dao.DummyDao
import m.tech.baseclean.framework.datasource.cache.model.DummyCacheEntity

@Database(
    entities = [
        DummyCacheEntity::class
    ],
    version = 1
)

//@TypeConverters(DataConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun dummyDao(): DummyDao

    companion object {
        const val DATABASE_NAME = "app_db"
    }
}