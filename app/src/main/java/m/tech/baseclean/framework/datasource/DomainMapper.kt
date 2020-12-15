package m.tech.baseclean.framework.datasource

interface DomainMapper<T, DomainModel> {

    fun toDomain(model: T): DomainModel

    fun fromDomain(domainModel: DomainModel): T

}