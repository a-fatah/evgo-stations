package io.evgo.stations

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

@Entity
data class ChargingStation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val name: String = "",
    val location: String = "", // will be a geo-location
    @ManyToMany
    @JoinTable(
        name = "charging_station_connector",
        joinColumns = [JoinColumn(name = "station_id")],
        inverseJoinColumns = [JoinColumn(name = "connector_id")]
    )
    val connectors: List<Connector> = emptyList(),
    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(
        name = "charging_station_amenity",
        joinColumns = [JoinColumn(name = "station_id")],
        inverseJoinColumns = [JoinColumn(name = "amenity_id")]
    )
    val amenities: List<Amenity> = emptyList(),
    val openingHours: String = "",
    val rating: Double = 0.0,
)

@Entity
data class Amenity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val name: String = "",
)

@Entity
data class Connector(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Enumerated(EnumType.STRING)
    @Column(name = "connectorType")
    val type: ConnectorType = ConnectorType.TYPE_2,
    val power: Int = 0, // in kW
    val price: Int = 0, // in cents
    val status: ConnectorStatus = ConnectorStatus.AVAILABLE
)

enum class ConnectorStatus {
    AVAILABLE, OCCUPIED
}

enum class ConnectorType {
    TYPE_1, TYPE_2, CCS, DC_FAST, CHADEMO
}

interface ConnectorRepository: JpaRepository<Connector, Int> {
}

interface ChargingStationRepository: JpaRepository<ChargingStation, Int> {
}
