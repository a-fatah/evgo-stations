package io.evgo.stations

import org.locationtech.jts.geom.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.hateoas.EntityModel
import org.springframework.hateoas.server.RepresentationModelProcessor

@RepositoryRestResource(path="stations", itemResourceRel = "station", collectionResourceRel = "stations")
interface ChargingStationRepository: JpaRepository<ChargingStation, Int> {
    @Query("SELECT c FROM ChargingStation c WHERE ST_Distance(c.location, :point) < :distance")
    fun findNearbyChargingStations(
        @Param("point") point: Point?,
        @Param("distance") distance: Double
    ): List<ChargingStation>
}

@RepositoryRestResource
interface ConnectorRepository: JpaRepository<Connector, Int> {
}

class ConnectorResourceProcessor : RepresentationModelProcessor<EntityModel<Connector>> {

    override fun process(model: EntityModel<Connector>): EntityModel<Connector> {
        TODO("Not yet implemented")
    }

}