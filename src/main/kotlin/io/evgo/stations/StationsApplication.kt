package io.evgo.stations

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StationsApplication

fun main(args: Array<String>) {
    runApplication<StationsApplication>(*args)
}
