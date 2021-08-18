package m.tech.baseclean.business.domain

import m.tech.baseclean.framework.datasource.cache.model.DummyEntity

data class DummyModel(
    val id: Int,
    val name: String,
    val desc: String
)

fun DummyModel.mapToEntity(): DummyEntity {
    return DummyEntity(id = id, name = name, desc = desc)
}
