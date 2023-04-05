package io.evgo.stations.controller

import io.evgo.stations.service.StationService
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.hateoas.CollectionModel
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@RepositoryRestController
class StationsController(
  private val stationService: StationService,
) {

  @GetMapping("/stations/nearby")
  fun nearby(
    @RequestParam("lat") lat: Double,
    @RequestParam("lng") lng: Double,
    @RequestParam("radius") radius: Double,
    assembler: PersistentEntityResourceAssembler
  ): ResponseEntity<CollectionModel<*>> {
    // find all stations within radius
    val nearbyStations = stationService.findNearby(lat, lng, radius)

    return ResponseEntity.ok(assembler.toCollectionModel(nearbyStations))
  }

}
