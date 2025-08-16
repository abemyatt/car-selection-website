# car-selection-website

## Technologies

- Docker & Docker Compose
- PostgreSQL

## Project Structure

### db/init

The db/init folder contains the postgreSQL scripts used to create the schema, tables, import data, and set permissions. It also contains a /data folder which uses sample .csv files for car data, provided by [carapi.app](https://carapi.app/features/vehicle-csv-download).

## Environment Variables

| Name                 | Description            | Required for            | Example Value |
| -------------------- | ---------------------- | ----------------------- | ------------- |
| ${POSTGRES_USER}     | The postgres user name | The PostgreSQL database | admin         |
| ${POSTGRES_PASSWORD} | The postgres password  | The PostgreSQL database | password      |

## Building and Running the Services

### via Docker

Run `docker compose up --build` to start the db.

## How to use the service

### PostgreSQL

The PostgreSQL service runs within a docker container called `cars_db`. You can access this with the following properties:

| Host      | Port | User             | Password             |
| --------- | ---- | ---------------- | -------------------- |
| localhost | 5432 | ${POSTGRES_USER} | ${POSTGRES_PASSWORD} |

## Running Tests

### Unit Tests
