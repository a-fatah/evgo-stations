package io.evgo.stations

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StationsApplication

fun main(args: Array<String>) {
    val stations = listOf<ChargingStation>(
        ChargingStation(
            name = "Station 1",
            address = "Address 1",
            location = "Location 1",
            connectors = listOf(
                Connector(
                    type = ConnectorType.TYPE_2,
                    power = 22,
                    status = ConnectorStatus.AVAILABLE
                ),
                Connector(
                    type = ConnectorType.CCS,
                    power = 50,
                    status = ConnectorStatus.AVAILABLE
                ),
                Connector(
                    type = ConnectorType.DC_FAST,
                    power = 150,
                    status = ConnectorStatus.AVAILABLE
                ),
            ),
            amenities = listOf("WC", "Shower"),
            openingHours = listOf("Mo-Fr 8-18", "Sa 10-16"),
            rating = 4.5
        ),
        ChargingStation(
            name = "Station 2",
            address = "Address 2",
            location = "Location 2",
            connectors = listOf(
                Connector(
                    type = ConnectorType.TYPE_2,
                    power = 22,
                    status = ConnectorStatus.AVAILABLE
                ),
                Connector(
                    type = ConnectorType.CCS,
                    power = 50,
                    status = ConnectorStatus.AVAILABLE
                ),
                Connector(
                    type = ConnectorType.DC_FAST,
                    power = 150,
                    status = ConnectorStatus.AVAILABLE
                ),
            ),
            amenities = listOf("WC", "Shower"),
            openingHours = listOf("Mo-Fr 8-18", "Sa 10-16"),
            rating = 4.5
        ),
    )
    runApplication<StationsApplication>(*args)
}

