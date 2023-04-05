package io.evgo.stations.repository

import io.evgo.stations.model.Amenity
import org.springframework.data.jpa.repository.JpaRepository

interface AmenitiesRepository: JpaRepository<Amenity, Int>
