package m.tech.baseclean.framework.datasource.network.mappers

import m.tech.baseclean.business.domain.Dummy
import m.tech.baseclean.framework.datasource.DomainMapper
import m.tech.baseclean.framework.datasource.network.model.response.DummyDto
import javax.inject.Inject

class DummyDtoMapper
@Inject
constructor() : DomainMapper<DummyDto, Dummy> {

    override fun toDomain(model: DummyDto): Dummy {
        return Dummy(
            model.id,
            model.title,
            model.body
        )
    }

    override fun fromDomain(domainModel: Dummy): DummyDto {
        return DummyDto(
            domainModel.id,
            domainModel.name,
            domainModel.desc,
            16112020
        )
    }

    fun toDomainList(list: List<DummyDto>): List<Dummy> = list.map {
        toDomain(it)
    }

    fun fromDomainList(list: List<Dummy>): List<DummyDto> = list.map {
        fromDomain(it)
    }

}