package io.evgo.stations

import jakarta.persistence.*


@Entity
data class Connector(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Enumerated(EnumType.STRING)
    @Column(name = "connectorType")
    val type: ConnectorType = ConnectorType.TYPE_2,
    val power: Int = 0, // in kW
    val price: Int = 0, // in cents
    val status: ConnectorStatus = ConnectorStatus.AVAILABLE
)


enum class ConnectorType {
    TYPE_1, TYPE_2, CCS, DC_FAST, CHADEMO
}


enum class ConnectorStatus {
    AVAILABLE, OCCUPIED
}
