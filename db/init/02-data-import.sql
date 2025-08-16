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
    trim_msrp NUMERIC,
    trim_invoice NUMERIC,
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
    body_length NUMERIC,
    body_width NUMERIC,
    body_height NUMERIC,
    body_wheel_base NUMERIC,
    body_front_track NUMERIC,
    body_rear_track NUMERIC,
    body_ground_clearance NUMERIC,
    body_cargo_capacity NUMERIC,
    body_max_cargo_capacity NUMERIC,
    body_curb_weight NUMERIC,
    body_gross_weight NUMERIC,
    body_max_payload NUMERIC,
    body_max_towing_capacity NUMERIC,
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
    engine_size NUMERIC,
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
    fuel_tank_capacity NUMERIC,
    combined_mpg NUMERIC,
    city_mpg NUMERIC,
    highway_mpg NUMERIC,
    range_city INT,
    range_highway INT,
    ev_combined_mpg NUMERIC,
    ev_city_mpg NUMERIC,
    ev_highway_mpg NUMERIC,
    ev_range INT,
    ev_kwh_per_100_mi NUMERIC,
    ev_charge_time_hr_240 NUMERIC,
    ev_battery_capacity NUMERIC,
    mileage_created TEXT,
    mileage_modified TEXT
);

COPY staging_makes FROM '/docker-entrypoint-initdb.d/data/makes-sample.csv' CSV HEADER;
COPY staging_models FROM '/docker-entrypoint-initdb.d/data/models-sample.csv' CSV HEADER;
COPY staging_submodels FROM '/docker-entrypoint-initdb.d/data/submodels-sample.csv' CSV HEADER;
COPY staging_trims FROM '/docker-entrypoint-initdb.d/data/trims-sample.csv' CSV HEADER;
COPY staging_bodies FROM '/docker-entrypoint-initdb.d/data/bodies-sample.csv' CSV HEADER;
COPY staging_engines FROM '/docker-entrypoint-initdb.d/data/engines-sample.csv' CSV HEADER;
COPY staging_mileages FROM '/docker-entrypoint-initdb.d/data/mileages-sample.csv' CSV HEADER;


-- Import from the temporary tables which contain the full csv data into the normalized tables
INSERT INTO makes
SELECT DISTINCT make_id, make_name, make_created::timestamp, make_modified::timestamp
FROM staging_makes;

INSERT INTO models
SELECT DISTINCT model_id, make_id, model_name, model_created::timestamp, model_modified::timestamp
FROM staging_models;

INSERT INTO model_years
SELECT DISTINCT model_id, model_year
FROM staging_models;

INSERT INTO submodels
SELECT DISTINCT submodel_id, model_id, model_year, submodel_name,
       submodel_created::timestamp, submodel_modified::timestamp
FROM staging_submodels;

INSERT INTO trims
SELECT DISTINCT trim_id, submodel_id, model_id, model_year,
       trim_name, trim_description, trim_msrp, trim_invoice,
       trim_created::timestamp, trim_modified::timestamp
FROM staging_trims;

INSERT INTO bodies
SELECT DISTINCT b.body_id, b.trim_id, b.body_type, b.body_doors, b.body_seats,
       b.body_length, b.body_width, b.body_height, b.body_wheel_base,
       b.body_front_track, b.body_rear_track, b.body_ground_clearance,
       b.body_cargo_capacity, b.body_max_cargo_capacity, b.body_curb_weight,
       b.body_gross_weight, b.body_max_payload, b.body_max_towing_capacity,
       b.body_created::timestamp, b.body_modified::timestamp
FROM staging_bodies b
JOIN trims t ON b.trim_id = t.trim_id;

INSERT INTO engines
SELECT DISTINCT e.engine_id, e.trim_id, e.engine_type, e.engine_fuel_type, e.engine_cylinders,
       e.engine_size, e.engine_horsepower_hp, e.engine_horsepower_rpm,
       e.engine_torque_ft_lbs, e.engine_torque_rpm, e.engine_valves,
       e.engine_valve_timing, e.engine_cam_type, e.engine_drive_type,
       e.engine_transmission,
       e.engine_created::timestamp, e.engine_modified::timestamp
FROM staging_engines e
JOIN trims t ON e.trim_id = t.trim_id;

INSERT INTO mileages
SELECT DISTINCT m.mileage_id, m.trim_id, m.fuel_tank_capacity,
       m.combined_mpg, m.city_mpg, m.highway_mpg, m.range_city, m.range_highway,
       m.ev_combined_mpg, m.ev_city_mpg, m.ev_highway_mpg, m.ev_range,
       m.ev_kwh_per_100_mi, m.ev_charge_time_hr_240, m.ev_battery_capacity,
       m.mileage_created::timestamp, m.mileage_modified::timestamp
FROM staging_mileages m
JOIN trims t ON m.trim_id = t.trim_id;

-- Drop the temporary tables, only used to import from the csv
DROP TABLE staging_makes, staging_models, staging_submodels,
           staging_trims, staging_bodies, staging_engines, staging_mileages;
