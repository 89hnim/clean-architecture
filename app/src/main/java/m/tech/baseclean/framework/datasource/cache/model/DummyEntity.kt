package m.tech.baseclean.framework.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import m.tech.baseclean.framework.datasource.cache.model.DummyEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class DummyEntity(

    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Int,

    @ColumnInfo(name = NAME)
    val name: String,

    @ColumnInfo(name = DESC)
    val desc: String
) {

    companion object {
        const val TABLE_NAME = "dummy"
        const val ID = "id"
        const val NAME = "name"
        const val DESC = "desc"
    }

}