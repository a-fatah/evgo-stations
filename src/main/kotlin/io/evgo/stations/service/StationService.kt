package io.evgo.stations.service

import io.evgo.stations.model.ChargingStation
import io.evgo.stations.repository.StationsRepository
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.springframework.stereotype.Service

interface StationService {
  fun findNearby(lat: Double, lng: Double, radius: Double): List<ChargingStation>
}

@Service
class StationServiceImpl(private val stationsRepository: StationsRepository): StationService {

  override fun findNearby(lat: Double, lng: Double, radius: Double): List<ChargingStation> {
    // create a point from the lat/lon
    val point = GeometryFactory().createPoint(Coordinate(lng, lat))
    point.srid = 4326

    val nearbyStations = stationsRepository.findNearbyChargingStations(point, radius)

    return nearbyStations
  }

}
