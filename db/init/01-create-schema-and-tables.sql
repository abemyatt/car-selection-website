-- =====================================================================
-- ** 01-create-schema-and-tables.sql **
-- Created at: 16/08/2025
-- 
-- Creates the appropriate tables within the schema
-- =====================================================================
CREATE SCHEMA IF NOT EXISTS car_app;

CREATE TABLE IF NOT EXISTS car_app.makes (
    make_id      INT PRIMARY KEY,
    make_name    TEXT NOT NULL,
    created_at   TIMESTAMP,
    updated_at   TIMESTAMP
);

CREATE TABLE IF NOT EXISTS car_app.models (
    model_id     INT PRIMARY KEY,
    make_id      INT NOT NULL REFERENCES car_app.makes(make_id),
    model_name   TEXT NOT NULL,
    created_at   TIMESTAMP,
    updated_at   TIMESTAMP
);

CREATE TABLE IF NOT EXISTS car_app.model_years (
    model_id     INT NOT NULL REFERENCES car_app.models(model_id),
    model_year   INT NOT NULL,
    PRIMARY KEY (model_id, model_year)
);

CREATE TABLE IF NOT EXISTS car_app.submodels (
    submodel_id   INT PRIMARY KEY,
    model_id      INT NOT NULL,
    model_year    INT NOT NULL,
    submodel_name TEXT NOT NULL,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    FOREIGN KEY (model_id, model_year)
        REFERENCES car_app.model_years(model_id, model_year)
);

CREATE TABLE IF NOT EXISTS car_app.trims (
    trim_id           INT PRIMARY KEY,
    submodel_id       INT NOT NULL REFERENCES car_app.submodels(submodel_id),
    model_id          INT NOT NULL,
    model_year        INT NOT NULL,
    trim_name         TEXT,
    trim_description  TEXT,
    trim_msrp         NUMERIC,
    trim_invoice      NUMERIC,
    created_at        TIMESTAMP,
    updated_at        TIMESTAMP,
    FOREIGN KEY (model_id, model_year)
        REFERENCES car_app.model_years(model_id, model_year)
);

CREATE TABLE IF NOT EXISTS car_app.bodies (
    body_id                 INT PRIMARY KEY,
    trim_id                 INT NOT NULL REFERENCES car_app.trims(trim_id),
    body_type               TEXT,
    body_doors              INT,
    body_seats              INT,
    body_length             NUMERIC,
    body_width              NUMERIC,
    body_height             NUMERIC,
    body_wheel_base         NUMERIC,
    body_front_track        NUMERIC,
    body_rear_track         NUMERIC,
    body_ground_clearance   NUMERIC,
    body_cargo_capacity     NUMERIC,
    body_max_cargo_capacity NUMERIC,
    body_curb_weight        NUMERIC,
    body_gross_weight       NUMERIC,
    body_max_payload        NUMERIC,
    body_max_towing_capacity NUMERIC,
    created_at              TIMESTAMP,
    updated_at              TIMESTAMP
);

CREATE TABLE IF NOT EXISTS car_app.engines (
    engine_id               INT PRIMARY KEY,
    trim_id                 INT NOT NULL REFERENCES car_app.trims(trim_id),
    engine_type             TEXT,
    engine_fuel_type        TEXT,
    engine_cylinders        TEXT,
    engine_size             NUMERIC,
    engine_horsepower_hp    INT,
    engine_horsepower_rpm   INT,
    engine_torque_ft_lbs    INT,
    engine_torque_rpm       INT,
    engine_valves           INT,
    engine_valve_timing     TEXT,
    engine_cam_type         TEXT,
    engine_drive_type       TEXT,
    engine_transmission     TEXT,
    created_at              TIMESTAMP,
    updated_at              TIMESTAMP
);

CREATE TABLE IF NOT EXISTS car_app.mileages (
    mileage_id              INT PRIMARY KEY,
    trim_id                 INT NOT NULL REFERENCES car_app.trims(trim_id),
    fuel_tank_capacity      NUMERIC,
    combined_mpg            NUMERIC,
    city_mpg                NUMERIC,
    highway_mpg             NUMERIC,
    range_city              INT,
    range_highway           INT,
    ev_combined_mpg         NUMERIC,
    ev_city_mpg             NUMERIC,
    ev_highway_mpg          NUMERIC,
    ev_range                INT,
    ev_kwh_per_100_mi       NUMERIC,
    ev_charge_time_hr_240   NUMERIC,
    ev_battery_capacity     NUMERIC,
    created_at              TIMESTAMP,
    updated_at              TIMESTAMP
);
