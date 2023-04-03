## Stations Service

This is a simple service that provides a list of stations and their current status.

### Charging Station

Charging station model contains the following fields:

* id - an identifier for the station (integer)

* name - a string that represents the name of the station

* location - a string that represents the geo location of the station

* connectors - a list of connectors that are available at the station

* amenities - a list of amenities that are available at the station

* openingHours - specifies the hours during which charging station is open

* rating - a double that represents the rating of the station


The station model is represented as JSON as follows:

    {
      "id": 1,
      "name": "ChargePoint",
      "location": "37.7749, 122.4194",
      "connectors": [
        {
          "id": 1,
          "type": "J1772"
        },
        {
          "id": 2,
          "type": "CHAdeMO"
        }
      ],
      "amenities": [
        {
          "id": 1,
          "name": "Restroom"
        },
        {
          "id": 2,
          "name": "WiFi"
        }
      ],
      "openingHours": "Mon-Sun 24hrs",
      "rating": 4.5
    }

### Connector

Conector is an entity and has an id field that is an integer and is generated automatically using the GenerationType.IDENTITY strategy. 

It also has a type field that is an enumerated type and is stored in the database as a string. The power and price fields are both integers, with power being in kW and price being in cents. Finally, the status field is a ConnectorStatus type and is set to AVAILABLE by default.

### Connector Type

This enum contains five constants: TYPE_1, TYPE_2, CCS, DC_FAST, and CHADEMO. These constants is used to represent different types of connectors.

### Connector Status

This enum has two constants, AVAILABLE and OCCUPIED, which is used to represent the status of a connector. The constants are used to determine whether a connector is available or occupied.

### Usage

To run the service, simply run `./gradlew bootRun` from the root directory.

Or you can use docker compose to run the service and a mysql database:

    docker-compose up


You need to define the following environment variables:

* `MYSQL_USER` - the username to use for the database
* `MYSQL_PASSWORD` - the password to use for the database
* `MYSQL_ROOT_PASSWORD` - the root password for the mysql database
* `MYSQL_DATABASE` - the name of the database to create on startup 

Or you can create an `.env` file in the root directory and add the following:

    MYSQL_USER=<<username>>
    MYSQL_PASSWORD=<<password>>
    MYSQL_ROOT_PASSWORD=<<root password>>
    MYSQL_DATABASE=<<database name>>

The service will be available at `http://localhost:8080`.

