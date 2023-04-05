package io.evgo.stations.controller

import io.evgo.stations.model.ChargingStation
import io.evgo.stations.service.StationService
import org.springframework.data.domain.PageRequest
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.data.web.PagedResourcesAssembler
import org.springframework.hateoas.CollectionModel
import org.springframework.hateoas.PagedModel
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
    @RequestParam("page", defaultValue = "1") page: Int,
    @RequestParam("size", defaultValue = "5") size: Int,
    assembler: PersistentEntityResourceAssembler,
    pagedResourcesAssembler: PagedResourcesAssembler<ChargingStation>
  ): ResponseEntity<PagedModel<*>> {
    val pageable = PageRequest.of(page, size)
    // find all stations within radius
    val nearbyStations = stationService.findNearby(lat, lng, radius, pageable)

    val pagedResource = pagedResourcesAssembler.toModel(nearbyStations)

    return ResponseEntity.ok(pagedResource)
  }

}
