package io.evgo.stations

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.RepresentationModelProcessor

@RepositoryRestResource(itemResourceRel = "station", collectionResourceRel = "stations")
interface ChargingStationRepository: JpaRepository<ChargingStation, Int> {
}

@RepositoryRestResource
interface ConnectorRepository: JpaRepository<Connector, Int> {
}

class ConnectorResourceProcessor : RepresentationModelProcessor<EntityModel<Connector>> {

    override fun process(model: EntityModel<Connector>): EntityModel<Connector> {
        TODO("Not yet implemented")
    }

}