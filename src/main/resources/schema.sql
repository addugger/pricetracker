CREATE SCHEMA IF NOT EXISTS demo_db;

CREATE TABLE IF NOT EXISTS demo_db.demo_table (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    middle_initial  CHAR,
    job VARCHAR(250),
    age SMALLINT NOT NULL,
    doe TIMESTAMP NOT NULL
);