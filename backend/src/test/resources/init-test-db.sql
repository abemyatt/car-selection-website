CREATE SCHEMA IF NOT EXISTS car_app;

CREATE TABLE
    IF NOT EXISTS car_app.makes (
        make_id INT PRIMARY KEY,
        make_name TEXT NOT NULL,
        created_at TIMESTAMP,
        updated_at TIMESTAMP
    );

CREATE TABLE
    IF NOT EXISTS car_app.models (
        model_id INT PRIMARY KEY,
        make_id INT NOT NULL REFERENCES car_app.makes (make_id),
        model_name TEXT NOT NULL,
        created_at TIMESTAMP,
        updated_at TIMESTAMP
    );

CREATE TABLE
    IF NOT EXISTS car_app.model_years (
        model_id INT NOT NULL REFERENCES car_app.models (model_id),
        model_year INT NOT NULL,
        PRIMARY KEY (model_id, model_year)
    );

CREATE TABLE
    IF NOT EXISTS car_app.submodels (
        submodel_id INT PRIMARY KEY,
        model_id INT NOT NULL,
        model_year INT NOT NULL,
        submodel_name TEXT NOT NULL,
        created_at TIMESTAMP,
        updated_at TIMESTAMP,
        FOREIGN KEY (model_id, model_year) REFERENCES car_app.model_years (model_id, model_year)
    );

CREATE TABLE
    IF NOT EXISTS car_app.trims (
        trim_id INT PRIMARY KEY,
        submodel_id INT NOT NULL REFERENCES car_app.submodels (submodel_id),
        model_id INT NOT NULL,
        model_year INT NOT NULL,
        trim_name TEXT,
        trim_description TEXT,
        trim_msrp REAL,
        trim_invoice REAL,
        created_at TIMESTAMP,
        updated_at TIMESTAMP,
        FOREIGN KEY (model_id, model_year) REFERENCES car_app.model_years (model_id, model_year)
    );

CREATE TABLE
    IF NOT EXISTS car_app.bodies (
        body_id INT PRIMARY KEY,
        trim_id INT NOT NULL REFERENCES car_app.trims (trim_id),
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
        created_at TIMESTAMP,
        updated_at TIMESTAMP
    );

CREATE TABLE
    IF NOT EXISTS car_app.engines (
        engine_id INT PRIMARY KEY,
        trim_id INT NOT NULL REFERENCES car_app.trims (trim_id),
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
        created_at TIMESTAMP,
        updated_at TIMESTAMP
    );

CREATE TABLE
    IF NOT EXISTS car_app.mileages (
        mileage_id INT PRIMARY KEY,
        trim_id INT NOT NULL REFERENCES car_app.trims (trim_id),
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
        created_at TIMESTAMP,
        updated_at TIMESTAMP
    );