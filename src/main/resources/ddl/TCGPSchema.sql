CREATE SCHEMA IF NOT EXISTS tcgp;

CREATE TABLE IF NOT EXISTS tcgp.`category` (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    modified_on TIMESTAMP,
    display_name VARCHAR(255),
    sealed_label VARCHAR(255),
    non_sealed_label VARCHAR(255),
    doe TIMESTAMP,
    dlu TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tcgp.`group` (
    id INT PRIMARY KEY,
    category_id INT,
    name VARCHAR(255),
    abbreviation VARCHAR(10),
    is_supplemental BOOLEAN,
    published_on TIMESTAMP,
    modified_on TIMESTAMP,
    doe TIMESTAMP,
    dlu TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES tcgp.`category`
);

CREATE TABLE iF NOT EXISTS tcgp.`rarity` (
    id INT PRIMARY KEY,
    category_id INT,
    name VARCHAR(255),
    abbreviation VARCHAR(10),
    doe TIMESTAMP,
    dlu TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES tcgp.`category`
);

CREATE TABLE IF NOT EXISTS tcgp.`language` (
    id INT PRIMARY KEY,
    category_id INT,
    name VARCHAR(255),
    abbreviation VARCHAR(10),
    doe TIMESTAMP,
    dlu TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES tcgp.`category`
);

CREATE TABLE IF NOT EXISTS tcgp.`condition` (
    id INT PRIMARY KEY,
    category_id INT,
    name VARCHAR(255),
    abbreviation VARCHAR(10),
    doe TIMESTAMP,
    dlu TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES tcgp.`category`
);

CREATE TABLE IF NOT EXISTS tcgp.`printing` (
    id INT PRIMARY KEY,
    category_id INT,
    name VARCHAR(255),
    modified_on TIMESTAMP,
    doe TIMESTAMP,
    dlu TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES tcgp.`category`
);

CREATE TABLE IF NOT EXISTS tcgp.`product` (
    id INT PRIMARY KEY,
    group_id INT,
    category_id INT,
    rarity_id INT,
    name VARCHAR(255),
    clean_name VARCHAR(255),
    url VARCHAR(2083),
    image_url VARCHAR(2083),
    modified_on TIMESTAMP,
    doe TIMESTAMP,
    dlu TIMESTAMP,
    FOREIGN KEY (group_id) REFERENCES tcgp.`group`,
    FOREIGN KEY (category_id) REFERENCES tcgp.`category`,
    FOREIGN KEY (rarity_id) REFERENCES tcgp.`rarity`
);

CREATE TABLE IF NOT EXISTS tcgp.`sku` (
    id INT PRIMARY KEY,
    product_id INT,
    language_id INT,
    printing_id INT,
    condition_id INT,
    doe TIMESTAMP,
    dlu TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES tcgp.`product`,
    FOREIGN KEY (language_id) REFERENCES tcgp.`language`,
    FOREIGN KEY (printing_id) REFERENCES tcgp.`printing`,
    FOREIGN KEY (condition_id) REFERENCES tcgp.`condition`
);