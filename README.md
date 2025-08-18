# car-selection-website

Objective: Build a website that helps a customer choose a car based on their needs. The site can use car data available https://carapi.app/features/vehicle-csv-download

Assumptions:

- This is a site which helps a customer choose a car. Based on that knowledge, I interpret that a user should be able to provide some data before a list of cars is returned. I chose common features of a car as filtering data.
- The data provided in the spec should be used.
- All technologies used are within the objective / spec.

## Technologies

- Docker & Docker Compose
- PostgreSQL
- Java
- SpringBoot
- React
- Typescript

## Project Structure

### db/init

The db/init folder contains the postgreSQL scripts used to create the schema, tables, import data, and set permissions. It also contains a /data folder which uses sample .csv files for car data, provided by [carapi.app](https://carapi.app/features/vehicle-csv-download).

## backend

This folder contains the backend service written in Java with SpringBoot.

## frontend

This folder contains the frontend service written in Typescript with React / Vite.

## Environment Variables

| Name                       | Description                          | Required for                                                                      | Example Value         |
| -------------------------- | ------------------------------------ | --------------------------------------------------------------------------------- | --------------------- |
| ${POSTGRES_ADMIN_USER}     | The postgres admin user name         | The PostgreSQL database                                                           | admin                 |
| ${POSTGRES_ADMIN_PASSWORD} | The postgres admin password          | The PostgreSQL database                                                           | password              |
| ${POSTGRES_APP_USER}       | The postgres app user name           | The PostgreSQL database                                                           | user                  |
| ${POSTGRES_APP_PASSWORD}   | The postgres app password            | The PostgreSQL database                                                           | test                  |
| ${FRONTEND_URL}            | The url for the frontend application | The backend cors configuration                                                    | http://localhost:5173 |
| ${BASE_PATH}               | The base path of the backend api     | The REST controller mapping                                                       | /api/cars             |
| ${BACKEND_PORT}            | The base path of the backend api     | The REST controller mapping                                                       | 8080                  |
| ${VITE_BACKEND_BASE_URL}   | The base path of the backend api     | The base url for the backend service                                              | http://localhost:8080 |
| ${VITE_BACKEND_BASE_PATH}  | The base path of the backend api     | The frontend to send requests to the correct location (should match backend_path) | /api/cars             |

## Building and Running the Services

### via Docker

Ensure the environment variables are configured, see Environment Setup.
Each service has a Dockerfile for easy setting up and running of the services. Run `docker compose up --build` to start the required services. The backend depends on the db to be running, and the frontend depends on the backend. All depend on environment variables.

## How to use the service

### Environment setup

Please see the .env.example file in the root of the project for example environment variables. These will need to be set before you can run the services.

### PostgreSQL

The PostgreSQL service runs within a docker container called `cars_db`. You can access this db via your preferred db viewer with the following properties:

| Host      | Port | User                   | Password                   |
| --------- | ---- | ---------------------- | -------------------------- |
| localhost | 5432 | ${POSTGRES_ADMIN_USER} | ${POSTGRES_ADMIN_PASSWORD} |

### Backend

The backend is a springboot rest service which has the following endpoints:
`GET /cars` - Has the query parameters make, model, bodyType, doors, fuelType, transmission for filtering. Returns information of cars.
`GET /cars/makes` Returns a specific make of a car and the id.
`GET /cars/models` Has the query parameter makeId for filtering by make. Returns a list of models related to the make.

### frontend

The frontend is used to help customers choose a car. It allows them to select a make and model based on what is stored in the DB. If they choose both, the results will show the specific values with different trim levels or engine sizes.
If they do not choose a model, they can further refine their choice in the refine page. This allows them to filter by features. Based on these features, the results will be shown on the results page (when the button is pressed).

Example urls:
http://localhost:5173/
http://localhost:5173/refine
http://localhost:5173/results
