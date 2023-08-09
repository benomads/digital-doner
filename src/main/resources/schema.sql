CREATE TABLE IF NOT EXISTS Doner_Order (
                                           id SERIAL PRIMARY KEY,
                                           delivery_Name VARCHAR(50) NOT NULL,
                                           delivery_Street VARCHAR(50) NOT NULL,
                                           delivery_City VARCHAR(50) NOT NULL,
                                           delivery_State VARCHAR(2) NOT NULL,
                                           delivery_Zip VARCHAR(10) NOT NULL,
                                           cc_number VARCHAR(16) NOT NULL,
                                           cc_expiration VARCHAR(5) NOT NULL,
                                           cc_cvv VARCHAR(3) NOT NULL,
                                           placed_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS Doner (
                                     id SERIAL PRIMARY KEY,
                                     name VARCHAR(50) NOT NULL,
                                     created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS Ingredient_Ref (
                                              ingredient VARCHAR(4) NOT NULL,
                                              doner BIGINT NOT NULL,
                                              doner_key BIGINT NOT NULL,
                                              FOREIGN KEY (doner) REFERENCES Doner(id)
);


CREATE TABLE IF NOT EXISTS Ingredient (
                                          id VARCHAR(4) NOT NULL,
                                          name VARCHAR(25) NOT NULL,
                                          type varchar (10) NOT NULL,
                                          PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS "User" (
    id SERIAL PRIMARY KEY,
    username varchar(25) NOT NULL,
    password varchar(25) NOT NULL,
    fullname varchar(60) NOT NULL,
    street varchar(50) NOT NULL,
    city varchar(50) NOT NULL,
    state varchar(2) NOT NULL,
    zip varchar(10) NOT NULL,
    phone_number varchar(25) NOT NULL
);


