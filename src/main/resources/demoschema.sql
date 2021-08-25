CREATE SCHEMA IF NOT EXISTS demo_db;

CREATE TABLE IF NOT EXISTS demo_db.demo_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    middle_initial  VARCHAR(10),
    job VARCHAR(255),
    age SMALLINT NOT NULL,
    doe TIMESTAMP NOT NULL
);