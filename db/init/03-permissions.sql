-- =====================================================================
-- ** 03-permissions.sql **
-- Created at: 16/08/2025
-- 
-- Gives the appuser (backend service) read-only permissions (SELECT)
-- =====================================================================
CREATE USER appuser
WITH
    PASSWORD 'test';

GRANT CONNECT ON DATABASE cars TO appuser;

GRANT USAGE ON SCHEMA car_app TO appuser;

GRANT
SELECT
    ON ALL TABLES IN SCHEMA car_app TO appuser;

ALTER DEFAULT PRIVILEGES IN SCHEMA car_app GRANT
SELECT
    ON TABLES TO appuser;