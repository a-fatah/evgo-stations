package io.evgo.stations.config

import io.evgo.stations.*
import io.evgo.stations.model.Amenity
import io.evgo.stations.model.ChargingStation
import io.evgo.stations.repository.AmenitiesRepository
import io.evgo.stations.repository.StationsRepository
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import java.util.*

@Component
@ConditionalOnProperty(name = ["init-db"], havingValue = "true")
class InitDatabase(
    private val stationsRepository: StationsRepository,
    private val connectorRepository: ConnectorRepository,
    private val amenitiesRepository: AmenitiesRepository,
): CommandLineRunner {

    override fun run(vararg args: String?) {
        val locations = listOf(
            "Alexander Platz 1, 10178 Berlin",
            "Kurfürstendamm 1, 10719 Berlin",
            "Friedrichstraße 1, 10117 Berlin",
            "Potsdamer Platz 1, 10785 Berlin",
            "Unter den Linden 1, 10117 Berlin",
            "Karl-Liebknecht-Straße 1, 10178 Berlin",
            "Leipziger Platz 1, 10117 Berlin",
        )

        val random = Random()

        val points = locations.map { location ->
            val latitude = random.nextDouble(52.3, 52.6)
            val longitude = random.nextDouble(13.2, 13.7)
            val point = GeometryFactory().createPoint(Coordinate(longitude, latitude))
            point.srid = 4326
            point to location
        }

        val connectors = connectorRepository.saveAll(randomConnectors())
        val amenities = amenitiesRepository.saveAll(randomAmenities())

        points.forEach { (point, location) ->
            val station = ChargingStation(
                name = randomStationName(),
                address = location,
                location = point,
                connectors = connectors,
                amenities = amenities,
                openingHours = listOf("Mo-Fr 8-18", "Sa 10-16").joinToString(", "),
                rating = Math.random() * 5
            )
            stationsRepository.save(station)
        }
    }

    private fun randomStationName(): String {
        val random = Random()
        val randomName = StringBuilder()
        val consonants = "bcdfghjklmnpqrstvwxyz"
        val vowels = "aeiou"
        val length = random.nextInt(3, 6)
        for (i in 0 until length) {
            if (i % 2 == 0) {
                randomName.append(consonants[random.nextInt(consonants.length)])
            } else {
                randomName.append(vowels[random.nextInt(vowels.length)])
            }
        }
        return randomName.toString().capitalize()
    }

    private fun randomAmenities(): List<Amenity> {
        val amenities = listOf("WC", "Coffee", "Parking", "Restaurant", "Snacks")

        val randomAmenities = mutableListOf<Amenity>()
        amenities.forEach { amenity ->
            if (Math.random() > 0.5) {
                randomAmenities.add(Amenity(name = amenity))
            }
        }
        return randomAmenities
    }

    private fun randomConnectors(): List<Connector> {
        val connectorTypes = listOf(
            ConnectorType.TYPE_1,
            ConnectorType.TYPE_2,
            ConnectorType.CCS,
            ConnectorType.DC_FAST,
        )

        val connectorPowers = listOf(3, 7, 22, 50)
        val connectorPrices = listOf(10, 20, 30, 40)

        // associate each connector type with a power
        val connectorTypePower = connectorTypes.zip(connectorPowers).toMap()

        val connectorTypePrice = connectorTypes.zip(connectorPrices).toMap()

        // create list of connectors with random status
        val connectors = connectorTypes.map { type ->
            Connector(
                type = type,
                power = connectorTypePower[type] ?: 0,
                price = connectorTypePrice[type] ?: 0,
                status = if (Math.random() > 0.5) ConnectorStatus.AVAILABLE else ConnectorStatus.OCCUPIED
            )
        }

        return connectors
    }

}
