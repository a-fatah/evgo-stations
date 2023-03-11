package io.evgo.stations

data class ChargingStation(
    val id: String,
    val name: String,
    val address: String,
    val location: Location,
    val addressInfo: AddressInfo,
    val connectors: List<Connector>,
    val pricing: Pricing,
    val amenities: List<String>,
    val openingHours: List<String>,
    val rating: Double
)

data class Connector(
    val id: String,
    val type: ConnectorType,
    val power: Int,
    val status: ConnectorStatus
)

enum class ConnectorStatus {
    AVAILABLE, OCCUPIED
}

enum class ConnectorType {
    TYPE_1, TYPE_2, CCS, DC_FAST, CHADEMO
}

data class Location(
    val latitude: Double,
    val longitude: Double
)

data class AddressInfo(
    val country: String,
    val city: String,
    val street: String,
    val houseNumber: String,
    val postalCode: String
)

data class Pricing(
    val currency: String,
    val pricePerKwh: Double
)
