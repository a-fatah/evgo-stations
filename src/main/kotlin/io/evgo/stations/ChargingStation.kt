package io.evgo.stations

import jakarta.persistence.*
import org.springframework.data.jpa.repository.JpaRepository

@Entity
data class ChargingStation(
    @Id
    @GeneratedValue
    val id: Int? = null,
    val name: String = "",
    val address: String = "",
    val location: String = "", // will be a geo-location
    @OneToOne
    val addressInfo: AddressInfo? = null,
    @OneToMany
    val connectors: List<Connector> = emptyList(),
    @OneToOne
    val pricing: Pricing? = null,
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
    val type: ConnectorType = ConnectorType.TYPE_2,
    val power: Int = 0, // in kW
    val status: ConnectorStatus = ConnectorStatus.AVAILABLE
)

enum class ConnectorStatus {
    AVAILABLE, OCCUPIED
}

enum class ConnectorType {
    TYPE_1, TYPE_2, CCS, DC_FAST, CHADEMO
}

@Entity
data class AddressInfo(
    @Id
    @GeneratedValue
    val id: Int? = null,
    val country: String = "DE",
    val city: String = "Berlin",
    val street: String = "",
    val houseNumber: String = "",
    val postalCode: String = ""
)

@Entity
data class Pricing(
    @Id
    @GeneratedValue
    val id: Int? = null,
    val currency: String = "EUR",
    val pricePerKwh: Int = 0, // in cents
)


interface ChargingStationRepository: JpaRepository<ChargingStation, Int> {
}
