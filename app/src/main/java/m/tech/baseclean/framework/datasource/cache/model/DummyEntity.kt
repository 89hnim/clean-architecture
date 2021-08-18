package m.tech.baseclean.framework.datasource.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import m.tech.baseclean.business.domain.DummyModel

@Entity(tableName = "dummy")
data class DummyEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "desc")
    val desc: String
)

fun DummyEntity.mapToDomain(): DummyModel {
    return DummyModel(
        id = id,
        name = name,
        desc = desc
    )
}

fun List<DummyEntity>.mapToDomain() = map { it.mapToDomain() }