-- =====================================================================
-- ** 02-data-import.sql **
-- Created at: 16/08/2025
-- 
-- Used to import data from the csv files into the tables
-- =====================================================================
CREATE TEMP TABLE staging_makes (
    make_id INT,
    make_name TEXT,
    make_created TEXT,
    make_modified TEXT
);

CREATE TEMP TABLE staging_models (
    make_id INT,
    model_id INT,
    model_year INT,
    make_name TEXT,
    model_name TEXT,
    model_created TEXT,
    model_modified TEXT
);

CREATE TEMP TABLE staging_submodels (
    make_id INT,
    model_id INT,
    submodel_id INT,
    model_year INT,
    make_name TEXT,
    model_name TEXT,
    submodel_name TEXT,
    submodel_created TEXT,
    submodel_modified TEXT
);

CREATE TEMP TABLE staging_trims (
    make_id INT,
    model_id INT,
    submodel_id INT,
    trim_id INT,
    model_year INT,
    make_name TEXT,
    model_name TEXT,
    submodel_name TEXT,
    trim_name TEXT,
    trim_description TEXT,
    trim_msrp REAL,
    trim_invoice REAL,
    trim_created TEXT,
    trim_modified TEXT
);

CREATE TEMP TABLE staging_bodies (
    make_id INT,
    model_id INT,
    submodel_id INT,
    trim_id INT,
    body_id INT,
    model_year INT,
    make_name TEXT,
    model_name TEXT,
    submodel_name TEXT,
    trim_name TEXT,
    trim_description TEXT,
    body_type TEXT,
    body_doors INT,
    body_seats INT,
    body_length REAL,
    body_width REAL,
    body_height REAL,
    body_wheel_base REAL,
    body_front_track REAL,
    body_rear_track REAL,
    body_ground_clearance REAL,
    body_cargo_capacity REAL,
    body_max_cargo_capacity REAL,
    body_curb_weight REAL,
    body_gross_weight REAL,
    body_max_payload REAL,
    body_max_towing_capacity REAL,
    body_created TEXT,
    body_modified TEXT
);

CREATE TEMP TABLE staging_engines (
    make_id INT,
    model_id INT,
    submodel_id INT,
    trim_id INT,
    engine_id INT,
    model_year INT,
    make_name TEXT,
    model_name TEXT,
    submodel_name TEXT,
    trim_name TEXT,
    trim_description TEXT,
    engine_type TEXT,
    engine_fuel_type TEXT,
    engine_cylinders TEXT,
    engine_size REAL,
    engine_horsepower_hp INT,
    engine_horsepower_rpm INT,
    engine_torque_ft_lbs INT,
    engine_torque_rpm INT,
    engine_valves INT,
    engine_valve_timing TEXT,
    engine_cam_type TEXT,
    engine_drive_type TEXT,
    engine_transmission TEXT,
    engine_created TEXT,
    engine_modified TEXT
);

CREATE TEMP TABLE staging_mileages (
    make_id INT,
    model_id INT,
    submodel_id INT,
    trim_id INT,
    mileage_id INT,
    model_year INT,
    make_name TEXT,
    model_name TEXT,
    submodel_name TEXT,
    trim_name TEXT,
    trim_description TEXT,
    fuel_tank_capacity REAL,
    combined_mpg REAL,
    city_mpg REAL,
    highway_mpg REAL,
    range_city INT,
    range_highway INT,
    ev_combined_mpg REAL,
    ev_city_mpg REAL,
    ev_highway_mpg REAL,
    ev_range INT,
    ev_kwh_per_100_mi REAL,
    ev_charge_time_hr_240 REAL,
    ev_battery_capacity REAL,
    mileage_created TEXT,
    mileage_modified TEXT
);

COPY staging_makes
FROM
    '/docker-entrypoint-initdb.d/data/makes-sample.csv' CSV HEADER;

COPY staging_models
FROM
    '/docker-entrypoint-initdb.d/data/models-sample.csv' CSV HEADER;

COPY staging_submodels
FROM
    '/docker-entrypoint-initdb.d/data/submodels-sample.csv' CSV HEADER;

COPY staging_trims
FROM
    '/docker-entrypoint-initdb.d/data/trims-sample.csv' CSV HEADER;

COPY staging_bodies
FROM
    '/docker-entrypoint-initdb.d/data/bodies-sample.csv' CSV HEADER;

COPY staging_engines
FROM
    '/docker-entrypoint-initdb.d/data/engines-sample.csv' CSV HEADER;

COPY staging_mileages
FROM
    '/docker-entrypoint-initdb.d/data/mileages-sample.csv' CSV HEADER;

-- Import from the temporary tables which contain the full csv data into the normalized tables
INSERT INTO
    car_app.makes
SELECT DISTINCT
    make_id,
    make_name,
    make_created::timestamp,
    make_modified::timestamp
FROM
    staging_makes;

INSERT INTO
    car_app.models
SELECT DISTINCT
    model_id,
    make_id,
    model_name,
    model_created::timestamp,
    model_modified::timestamp
FROM
    staging_models;

INSERT INTO
    car_app.model_years
SELECT DISTINCT
    model_id,
    model_year
FROM
    staging_models;

INSERT INTO
    car_app.submodels
SELECT DISTINCT
    submodel_id,
    model_id,
    model_year,
    submodel_name,
    submodel_created::timestamp,
    submodel_modified::timestamp
FROM
    staging_submodels;

INSERT INTO
    car_app.trims
SELECT DISTINCT
    trim_id,
    submodel_id,
    model_id,
    model_year,
    trim_name,
    trim_description,
    trim_msrp,
    trim_invoice,
    trim_created::timestamp,
    trim_modified::timestamp
FROM
    staging_trims;

INSERT INTO
    car_app.bodies
SELECT DISTINCT
    b.body_id,
    b.trim_id,
    b.body_type,
    b.body_doors,
    b.body_seats,
    b.body_length,
    b.body_width,
    b.body_height,
    b.body_wheel_base,
    b.body_front_track,
    b.body_rear_track,
    b.body_ground_clearance,
    b.body_cargo_capacity,
    b.body_max_cargo_capacity,
    b.body_curb_weight,
    b.body_gross_weight,
    b.body_max_payload,
    b.body_max_towing_capacity,
    b.body_created::timestamp,
    b.body_modified::timestamp
FROM
    staging_bodies b
    JOIN car_app.trims t ON b.trim_id = t.trim_id;

INSERT INTO
    car_app.engines
SELECT DISTINCT
    e.engine_id,
    e.trim_id,
    e.engine_type,
    e.engine_fuel_type,
    e.engine_cylinders,
    e.engine_size,
    e.engine_horsepower_hp,
    e.engine_horsepower_rpm,
    e.engine_torque_ft_lbs,
    e.engine_torque_rpm,
    e.engine_valves,
    e.engine_valve_timing,
    e.engine_cam_type,
    e.engine_drive_type,
    e.engine_transmission,
    e.engine_created::timestamp,
    e.engine_modified::timestamp
FROM
    staging_engines e
    JOIN car_app.trims t ON e.trim_id = t.trim_id;

INSERT INTO
    car_app.mileages
SELECT DISTINCT
    m.mileage_id,
    m.trim_id,
    m.fuel_tank_capacity,
    m.combined_mpg,
    m.city_mpg,
    m.highway_mpg,
    m.range_city,
    m.range_highway,
    m.ev_combined_mpg,
    m.ev_city_mpg,
    m.ev_highway_mpg,
    m.ev_range,
    m.ev_kwh_per_100_mi,
    m.ev_charge_time_hr_240,
    m.ev_battery_capacity,
    m.mileage_created::timestamp,
    m.mileage_modified::timestamp
FROM
    staging_mileages m
    JOIN car_app.trims t ON m.trim_id = t.trim_id;

-- Drop the temporary tables, only used to import from the csv
DROP TABLE staging_makes,
staging_models,
staging_submodels,
staging_trims,
staging_bodies,
staging_engines,
staging_mileages;