package io.evgo.stations

import org.locationtech.jts.geom.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path="stations", itemResourceRel = "station", collectionResourceRel = "stations")
interface ChargingStationRepository: JpaRepository<ChargingStation, Int> {
    @Query("SELECT c FROM ChargingStation c WHERE ST_Distance(c.location, :point) < :distance")
    fun findNearbyChargingStations(
        @Param("point") point: Point?,
        @Param("distance") distance: Double
    ): List<ChargingStation>
}

@RepositoryRestResource
interface ConnectorRepository: JpaRepository<Connector, Int>
