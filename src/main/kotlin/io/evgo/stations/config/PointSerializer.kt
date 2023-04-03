package io.evgo.stations.config

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.locationtech.jts.geom.Point

class PointSerializer: JsonSerializer<Point>() {
    override fun serialize(value: Point, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeStartObject()
        gen.writeNumberField("longitude", value.x)
        gen.writeNumberField("latitude", value.y)
        gen.writeEndObject()
    }
}
