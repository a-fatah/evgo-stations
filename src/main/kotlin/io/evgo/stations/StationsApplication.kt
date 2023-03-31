package io.evgo.stations

import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.*

@SpringBootApplication
class StationsApplication {

    @Bean
    @ConditionalOnProperty(name = ["init-db"], havingValue = "true")
    fun initDb(
        chargingStationRepository: ChargingStationRepository,
        connectorRepository: ConnectorRepository
    ) = CommandLineRunner {

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
            point to location
        }

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
        var connectors = connectorTypes.map { type ->
            Connector(
                type = type,
                power = connectorTypePower[type] ?: 0,
                price = connectorTypePrice[type] ?: 0,
                status = if (Math.random() > 0.5) ConnectorStatus.AVAILABLE else ConnectorStatus.OCCUPIED
            )
        }

        // save connectors to database
        connectors = connectorRepository.saveAll(connectors)

        val amenities = listOf("WC", "Coffee", "Parking", "Restaurant", "Snacks")

        // create a function that returns a random list of amenities
        fun randomAmenities(): List<Amenity> {
            val randomAmenities = mutableListOf<Amenity>()
            amenities.forEach { amenity ->
                if (Math.random() > 0.5) {
                    randomAmenities.add(Amenity(name = amenity))
                }
            }
            return randomAmenities
        }

        // create a method to generate random realistic station names
        fun randomStationName(): String {
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

        points.forEach { (point, location) ->
            val station = ChargingStation(
                name = randomStationName(),
                address = location,
                location = point,
                connectors = connectors,
                amenities = randomAmenities(),
                openingHours = listOf("Mo-Fr 8-18", "Sa 10-16").joinToString(", "),
                rating = Math.random() * 5
            )
            chargingStationRepository.save(station)
        }
    }

}


fun main(args: Array<String>) {
    runApplication<StationsApplication>(*args)
}
