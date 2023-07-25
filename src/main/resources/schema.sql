CREATE TABLE IF NOT EXISTS Doner_Order (
    id INT GENERATED ALWAYS AS IDENTITY,
    delivery_Name varchar(50) not null,
    delivery_Street varchar(50) not null,
    delivery_City varchar(50) not null,
    delivery_State varchar(2) not null,
    delivery_Zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placed_at timestamp not null
);

create table if not exists Doner (
      id INT GENERATED ALWAYS AS IDENTITY,
     name varchar(50) not null,
     doner_order bigint not null,
     doner_order_key bigint not null,
     created_at timestamp not null
);

create table if not exists Ingredient_Ref (
    ingredient varchar(4) not null,
    doner bigint not null,
    doner_key bigint not null
);

create table if not exists Ingredient (
     id varchar(4) not null,
     name varchar(25) not null,
     type varchar(10) not null
);

alter table Doner
    add foreign key (doner_order) references Doner_Order(id);
alter table Ingredient_Ref
    add foreign key (ingredient) references Ingredient(id);
