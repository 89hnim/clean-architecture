package m.tech.baseclean.framework.datasource.cache.mappers

import m.tech.baseclean.business.domain.Dummy
import m.tech.baseclean.framework.datasource.EntityMapper
import m.tech.baseclean.framework.datasource.cache.model.DummyCacheEntity
import javax.inject.Inject

class DummyCacheMapper
@Inject
constructor() : EntityMapper<DummyCacheEntity, Dummy> {

    override fun fromEntity(entity: DummyCacheEntity): Dummy {
        return Dummy(
            entity.id,
            entity.name,
            entity.desc
        )
    }

    override fun toEntity(domainModel: Dummy): DummyCacheEntity {
        return DummyCacheEntity(
            domainModel.id,
            domainModel.name,
            domainModel.desc
        )
    }

    fun fromEntities(list: List<DummyCacheEntity>): List<Dummy> = list.map {
        fromEntity(it)
    }

    fun toEntities(list: List<Dummy>): List<DummyCacheEntity> = list.map {
        toEntity(it)
    }

}