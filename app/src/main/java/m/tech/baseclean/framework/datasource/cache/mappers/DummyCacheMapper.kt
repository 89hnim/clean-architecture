package m.tech.baseclean.framework.datasource.cache.mappers

import m.tech.baseclean.business.domain.Dummy
import m.tech.baseclean.framework.datasource.DomainMapper
import m.tech.baseclean.framework.datasource.cache.model.DummyCacheEntity
import javax.inject.Inject

class DummyCacheMapper
@Inject
constructor() : DomainMapper<DummyCacheEntity, Dummy> {

    override fun toDomain(model: DummyCacheEntity): Dummy {
        return Dummy(
            model.id,
            model.name,
            model.desc
        )
    }

    override fun fromDomain(domainModel: Dummy): DummyCacheEntity {
        return DummyCacheEntity(
            domainModel.id,
            domainModel.name,
            domainModel.desc
        )
    }

    fun toDomainList(list: List<DummyCacheEntity>): List<Dummy> = list.map {
        toDomain(it)
    }

    fun fromDomainList(list: List<Dummy>): List<DummyCacheEntity> = list.map {
        fromDomain(it)
    }

}