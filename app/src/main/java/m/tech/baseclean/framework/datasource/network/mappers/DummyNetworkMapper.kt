package m.tech.baseclean.framework.datasource.network.mappers

import m.tech.baseclean.business.domain.Dummy
import m.tech.baseclean.framework.datasource.DomainMapper
import m.tech.baseclean.framework.datasource.network.model.response.DummyNetworkEntity
import javax.inject.Inject

class DummyNetworkMapper
@Inject
constructor() : DomainMapper<DummyNetworkEntity, Dummy> {

    override fun toDomain(model: DummyNetworkEntity): Dummy {
        return Dummy(
            model.id,
            model.title,
            model.body
        )
    }

    override fun fromDomain(domainModel: Dummy): DummyNetworkEntity {
        return DummyNetworkEntity(
            domainModel.id,
            domainModel.name,
            domainModel.desc,
            16112020
        )
    }

    fun toDomainList(list: List<DummyNetworkEntity>): List<Dummy> = list.map {
        toDomain(it)
    }

    fun fromDomainList(list: List<Dummy>): List<DummyNetworkEntity> = list.map {
        fromDomain(it)
    }

}