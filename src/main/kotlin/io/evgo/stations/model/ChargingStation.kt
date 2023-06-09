package io.evgo.stations.model

import io.evgo.stations.Connector
import jakarta.persistence.*
import org.locationtech.jts.geom.Point


@Entity
data class ChargingStation(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val name: String = "",
    val address: String = "",
    @Column(columnDefinition = "geometry(Point,4326)")
    val location: Point? = null, // will be a geolocation
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "charging_station_connector",
        joinColumns = [JoinColumn(name = "station_id")],
        inverseJoinColumns = [JoinColumn(name = "connector_id")]
    )
    val connectors: List<Connector> = emptyList(),
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "charging_station_amenity",
        joinColumns = [JoinColumn(name = "station_id")],
        inverseJoinColumns = [JoinColumn(name = "amenity_id")]
    )
    val amenities: List<Amenity> = emptyList(),
    val openingHours: String = "",
    val rating: Double = 0.0,
)
