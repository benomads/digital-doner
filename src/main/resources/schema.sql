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
                                     doner_order BIGINT NOT NULL,
                                     created_at TIMESTAMP NOT NULL,
                                     FOREIGN KEY (doner_order) REFERENCES Doner_Order(id)
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
                                          type VARCHAR(10) NOT NULL,
                                          PRIMARY KEY (id)
);
