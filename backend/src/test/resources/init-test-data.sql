-- Makes
INSERT INTO car_app.makes (make_id, make_name, created_at, updated_at)
VALUES
(1, 'Audi', NOW(), NOW()),
(2, 'BMW', NOW(), NOW()),
(3, 'Volkswagen', NOW(), NOW());

-- Models
INSERT INTO car_app.models (model_id, make_id, model_name, created_at, updated_at)
VALUES
(1, 1, 'A4', NOW(), NOW()),
(2, 1, 'Q5', NOW(), NOW()),
(3, 2, 'M3', NOW(), NOW()),
(4, 2, 'X5', NOW(), NOW()),
(5, 3, 'Golf', NOW(), NOW()),
(6, 3, 'Passat', NOW(), NOW());

-- Model Years
INSERT INTO car_app.model_years (model_id, model_year)
VALUES
(1, 2024), (1, 2025),
(2, 2024), (2, 2025),
(3, 2024), (3, 2025),
(4, 2024), (4, 2025),
(5, 2024), (5, 2025),
(6, 2024), (6, 2025);

-- Submodels
INSERT INTO car_app.submodels (submodel_id, model_id, model_year, submodel_name, created_at, updated_at)
VALUES
(1, 1, 2024, 'A4 Premium', NOW(), NOW()),
(2, 1, 2025, 'A4 Premium Plus', NOW(), NOW()),
(3, 2, 2024, 'Q5 Standard', NOW(), NOW()),
(4, 2, 2025, 'Q5 Luxury', NOW(), NOW()),
(5, 3, 2024, 'M3 Base', NOW(), NOW()),
(6, 3, 2025, 'M3 Competition', NOW(), NOW()),
(7, 4, 2024, 'X5 Base', NOW(), NOW()),
(8, 4, 2025, 'X5 M', NOW(), NOW()),
(9, 5, 2024, 'Golf S', NOW(), NOW()),
(10,5, 2025, 'Golf SE', NOW(), NOW()),
(11,6, 2024, 'Passat Base', NOW(), NOW()),
(12,6, 2025, 'Passat SEL', NOW(), NOW());

-- Trims
INSERT INTO car_app.trims (trim_id, submodel_id, model_id, model_year, trim_name, trim_description, trim_msrp, trim_invoice, created_at, updated_at)
VALUES
(1, 1, 1, 2024, 'Premium', 'Audi A4 Premium 2024', 40000.0, 38000.0, NOW(), NOW()),
(2, 2, 1, 2025, 'Premium Plus', 'Audi A4 Premium Plus 2025', 45000.0, 42000.0, NOW(), NOW()),
(3, 3, 2, 2024, 'Standard', 'Audi Q5 Standard 2024', 42000.0, 40000.0, NOW(), NOW()),
(4, 4, 2, 2025, 'Luxury', 'Audi Q5 Luxury 2025', 50000.0, 47000.0, NOW(), NOW()),
(5, 5, 3, 2024, 'Base', 'BMW M3 Base 2024', 60000.0, 57000.0, NOW(), NOW()),
(6, 6, 3, 2025, 'Competition', 'BMW M3 Competition 2025', 75000.0, 72000.0, NOW(), NOW()),
(7, 7, 4, 2024, 'Base', 'BMW X5 Base 2024', 65000.0, 62000.0, NOW(), NOW()),
(8, 8, 4, 2025, 'M', 'BMW X5 M 2025', 85000.0, 82000.0, NOW(), NOW()),
(9, 9, 5, 2024, 'S', 'VW Golf S 2024', 25000.0, 23000.0, NOW(), NOW()),
(10,10, 5, 2025, 'SE', 'VW Golf SE 2025', 27000.0, 25000.0, NOW(), NOW()),
(11,11, 6, 2024, 'Base', 'VW Passat Base 2024', 28000.0, 26000.0, NOW(), NOW()),
(12,12, 6, 2025, 'SEL', 'VW Passat SEL 2025', 32000.0, 30000.0, NOW(), NOW());

-- Bodies
INSERT INTO car_app.bodies (body_id, trim_id, body_type, body_doors, body_seats, body_length, body_width, body_height, body_wheel_base,
                            body_front_track, body_rear_track, body_ground_clearance, body_cargo_capacity, body_max_cargo_capacity,
                            body_curb_weight, body_gross_weight, body_max_payload, body_max_towing_capacity, created_at, updated_at)
VALUES
(1, 1, 'Sedan', 4, 5, 185.0, 72.0, 56.0, 110.0, 61.0, 62.0, 6.0, 12.0, 14.0, 3500.0, 4000.0, 1000.0, 2000.0, NOW(), NOW()),
(2, 2, 'Sedan', 4, 5, 186.0, 72.5, 56.2, 111.0, 61.2, 62.5, 6.1, 12.5, 14.5, 3550.0, 4050.0, 1000.0, 2000.0, NOW(), NOW());


-- Engines
INSERT INTO car_app.engines (engine_id, trim_id, engine_type, engine_fuel_type, engine_cylinders, engine_size, engine_horsepower_hp,
                             engine_horsepower_rpm, engine_torque_ft_lbs, engine_torque_rpm, engine_valves, engine_valve_timing, engine_cam_type,
                             engine_drive_type, engine_transmission, created_at, updated_at)
VALUES
(1, 1, 'Inline-4', 'Petrol', '4', 2.0, 250, 5000, 273, 1600, 16, 'DOHC', 'VVT', 'AWD', 'Automatic', NOW(), NOW()),
(2, 2, 'Inline-4', 'Petrol', '4', 2.0, 260, 5200, 280, 1700, 16, 'DOHC', 'VVT', 'AWD', 'Automatic', NOW(), NOW());

-- Mileages
INSERT INTO car_app.mileages (mileage_id, trim_id, fuel_tank_capacity, combined_mpg, city_mpg, highway_mpg,
                              range_city, range_highway, ev_combined_mpg, ev_city_mpg, ev_highway_mpg, ev_range,
                              ev_kwh_per_100_mi, ev_charge_time_hr_240, ev_battery_capacity, created_at, updated_at)
VALUES
(1, 1, 15.0, 30.0, 25.0, 35.0, 375, 525, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NOW(), NOW()),
(2, 2, 15.0, 31.0, 26.0, 36.0, 390, 540, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NOW(), NOW());
