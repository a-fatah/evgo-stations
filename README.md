## Stations Service

This is a simple service that provides a list of stations and their current status.

### Usage

To run the service, simply run `./gradlew bootRun` from the root directory.

Or you can use docker compose to run the service and a mysql database:

    docker-compose up


You need to define the following environment variables:

* `MYSQL_USER` - the username to use for the database
* `MYSQL_PASSWORD` - the password to use for the database
* `MYSQL_ROOT_PASSWORD` - the root password for the mysql database
* `MYSQL_DATABASE` - the name of the database to create on startup 

The service will be available at `http://localhost:8080`.

