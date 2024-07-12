create table "product" (
        "price" float(53),
        "quantity" integer,
        "id" bigint not null,
        "description" varchar(100),
        "name" varchar(255),
        primary key ("id")
    );
create sequence "product_seq" start with 1 increment by 1