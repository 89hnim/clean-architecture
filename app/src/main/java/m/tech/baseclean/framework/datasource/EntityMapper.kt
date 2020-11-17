package m.tech.baseclean.framework.datasource

interface EntityMapper<Entity, DomainModel> {

    fun fromEntity(entity: Entity): DomainModel

    fun toEntity(domainModel: DomainModel): Entity

}