package io.evgo.stations

data class ChargingSession(
    val id: String,
    val stationId: String,
    val connectorId: String,
    val startedAt: String,
    val finishedAt: String,
    val energyDelivered: Double,
    val payment: Double,
    val status: ChargingSessionStatus
)

enum class ChargingSessionStatus {
    IN_PROGRESS, FINISHED
}
