package m.tech.baseclean.framework.datasource.network.mappers

import m.tech.baseclean.business.domain.Dummy
import m.tech.baseclean.framework.datasource.EntityMapper
import m.tech.baseclean.framework.datasource.network.model.response.DummyNetworkEntity
import javax.inject.Inject

class DummyNetworkMapper
@Inject
constructor() : EntityMapper<DummyNetworkEntity, Dummy> {

    override fun fromEntity(entity: DummyNetworkEntity): Dummy {
        return Dummy(
            entity.id,
            entity.title,
            entity.body
        )
    }

    override fun toEntity(domainModel: Dummy): DummyNetworkEntity {
        return DummyNetworkEntity(
            domainModel.id,
            domainModel.name,
            domainModel.desc,
            16112020
        )
    }

    fun fromEntities(list: List<DummyNetworkEntity>): List<Dummy> = list.map {
        fromEntity(it)
    }

    fun toEntities(list: List<Dummy>): List<DummyNetworkEntity> = list.map {
        toEntity(it)
    }

}