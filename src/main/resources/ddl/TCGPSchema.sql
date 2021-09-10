CREATE SCHEMA IF NOT EXISTS tcgp;

CREATE TABLE IF NOT EXISTS tcgp.tcgp_category (
    id INT PRIMARY KEY,
    name VARCHAR(255),
    modified_on TIMESTAMP,
    display_name VARCHAR(255),
    sealed_label VARCHAR(255),
    non_sealed_label VARCHAR(255),
    doe TIMESTAMP,
    dlu TIMESTAMP
);

CREATE TABLE IF NOT EXISTS tcgp.tcgp_group (
    id INT PRIMARY KEY,
    category_id INT,
    name VARCHAR(255),
    abbreviation VARCHAR(10),
    is_supplemental BOOLEAN,
    published_on TIMESTAMP,
    modified_on TIMESTAMP,
    doe TIMESTAMP,
    dlu TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES tcgp.tcgp_category
);

CREATE TABLE iF NOT EXISTS tcgp.tcgp_rarity (
    id INT PRIMARY KEY,
    category_id INT,
    name VARCHAR(255),
    abbreviation VARCHAR(10),
    doe TIMESTAMP,
    dlu TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES tcgp.tcgp_category
);

CREATE TABLE IF NOT EXISTS tcgp.tcgp_language (
    id INT PRIMARY KEY,
    category_id INT,
    name VARCHAR(255),
    abbreviation VARCHAR(10),
    doe TIMESTAMP,
    dlu TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES tcgp.tcgp_category
);

CREATE TABLE IF NOT EXISTS tcgp.tcgp_condition (
    id INT PRIMARY KEY,
    category_id INT,
    name VARCHAR(255),
    abbreviation VARCHAR(10),
    doe TIMESTAMP,
    dlu TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES tcgp.tcgp_category
);

CREATE TABLE IF NOT EXISTS tcgp.tcgp_printing (
    id INT PRIMARY KEY,
    category_id INT,
    name VARCHAR(255),
    modified_on TIMESTAMP,
    doe TIMESTAMP,
    dlu TIMESTAMP,
    FOREIGN KEY (category_id) REFERENCES tcgp.tcgp_category
);

CREATE TABLE IF NOT EXISTS tcgp.tcgp_product (
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
    FOREIGN KEY (group_id) REFERENCES tcgp.tcgp_group,
    FOREIGN KEY (category_id) REFERENCES tcgp.tcgp_category,
    FOREIGN KEY (rarity_id) REFERENCES tcgp.tcgp_rarity
);

CREATE TABLE IF NOT EXISTS tcgp.tcgp_sku (
    id INT PRIMARY KEY,
    product_id INT,
    language_id INT,
    printing_id INT,
    condition_id INT,
    doe TIMESTAMP,
    dlu TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES tcgp.tcgp_product,
    FOREIGN KEY (language_id) REFERENCES tcgp.tcgp_language,
    FOREIGN KEY (printing_id) REFERENCES tcgp.tcgp_printing,
    FOREIGN KEY (condition_id) REFERENCES tcgp.tcgp_condition
);