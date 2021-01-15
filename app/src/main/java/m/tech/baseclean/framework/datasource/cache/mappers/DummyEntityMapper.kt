package m.tech.baseclean.framework.datasource.cache.mappers

import m.tech.baseclean.business.domain.Dummy
import m.tech.baseclean.framework.datasource.DomainMapper
import m.tech.baseclean.framework.datasource.cache.model.DummyEntity
import javax.inject.Inject

class DummyEntityMapper
@Inject
constructor() : DomainMapper<DummyEntity, Dummy> {

    override fun toDomain(model: DummyEntity): Dummy {
        return Dummy(
            model.id,
            model.name,
            model.desc
        )
    }

    override fun fromDomain(domainModel: Dummy): DummyEntity {
        return DummyEntity(
            domainModel.id,
            domainModel.name,
            domainModel.desc
        )
    }

    fun toDomainList(list: List<DummyEntity>): List<Dummy> = list.map {
        toDomain(it)
    }

    fun fromDomainList(list: List<Dummy>): List<DummyEntity> = list.map {
        fromDomain(it)
    }

}