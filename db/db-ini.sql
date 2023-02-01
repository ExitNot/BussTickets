--------
-- Drop all tables
--------

-- SELECT	pg_terminate_backend (pid)
-- FROM	pg_stat_activity
-- WHERE	pg_stat_activity.datname = 'postgres';

-- DROP DATABASE ticket_ WITH (FORCE);
-- CREATE DATABASE hotel ENCODING 'UTF-8';

DROP SCHEMA IF EXISTS public CASCADE;

CREATE SCHEMA public;

--------
-- Creating tables
--------

CREATE TABLE users (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
--                    password VARCHAR(100) NOT NULL, -- hashed pwd in SHA-256
                    name VARCHAR NOT NULL,
                    surname VARCHAR NOT NULL,
                    middlename VARCHAR NOT NULL);

CREATE TABLE payments (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                    user_id INT REFERENCES users(id),
                    price FLOAT NOT NULL,
                    payment_status VARCHAR(10) NOT NULL);

CREATE TABLE stations (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                    name VARCHAR NOT NULL);

CREATE TABLE routes (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                    s_from INT REFERENCES stations(id),
                    s_to INT REFERENCES stations(id),
                    departure TIME NOT NULL,
                    price FLOAT NOT NULL,
                    avail_cnt INT);

CREATE TABLE tickets (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                    route_id INT REFERENCES routes(id),
                    user_id INT REFERENCES users(id),
                    payment_id INT REFERENCES payments(id));


--------
-- Inserting default values
--------

INSERT INTO users  -- temporary values for dev process --
    (name, surname, middlename)
VALUES
    ('User', 'User', 'User');

INSERT INTO stations
    (name)
VALUES
    ('Kyiv'), ('Dnipro'), ('Lviv');

INSERT INTO routes
    (s_from, s_to, departure, price, avail_cnt)
VALUES
    (1, 2, '12:30:00', 20.5, 50),
    (1, 3, '14:40:00', 25.5, 50);

insert into tickets
	(route_id, user_id, payment_id)
values
	(1, 1, null)