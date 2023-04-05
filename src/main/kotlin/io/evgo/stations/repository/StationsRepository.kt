package io.evgo.stations.repository

import io.evgo.stations.model.ChargingStation
import org.locationtech.jts.geom.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path="stations", itemResourceRel = "station", collectionResourceRel = "stations")
interface StationsRepository: JpaRepository<ChargingStation, Int> {
  @Query("SELECT c FROM ChargingStation c WHERE ST_Distance(c.location, :point) < :radius")
  fun findNearbyChargingStations(
    @Param("point") point: Point?,
    @Param("radius") radius: Double
  ): List<ChargingStation>
}
