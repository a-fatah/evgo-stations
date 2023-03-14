package io.evgo.stations

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(itemResourceRel = "station", collectionResourceRel = "stations")
interface ChargingStationRepository: JpaRepository<ChargingStation, Int> {
}

@RepositoryRestResource
interface ConnectorRepository: JpaRepository<Connector, Int> {
}