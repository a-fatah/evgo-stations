package io.evgo.stations

import jakarta.persistence.*

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
