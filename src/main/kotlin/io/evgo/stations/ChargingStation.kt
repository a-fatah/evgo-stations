package io.evgo.stations

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDate

@Entity
data class ChargingStation(
    @Id
    @GeneratedValue
    val id: Int? = null,
    val name: String = "",
    val address: String = "",
    val location: String = "", // will be a geo-location
    @OneToMany
    val connectors: List<Connector> = emptyList(),
    @ElementCollection
    val amenities: List<String> = emptyList(),
    @ElementCollection
    val openingHours: List<String> = emptyList(),
    val rating: Double = 0.0,
)

@Entity
data class Connector(
    @Id
    @GeneratedValue
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


interface ChargingStationRepository: JpaRepository<ChargingStation, Int> {
}
