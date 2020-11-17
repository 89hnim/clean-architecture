package m.tech.baseclean.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import m.tech.baseclean.framework.datasource.cache.database.AppDatabase
import m.tech.baseclean.framework.datasource.cache.database.dao.DummyDao

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideDummyDao(db: AppDatabase): DummyDao {
        return db.dummyDao()
    }

}