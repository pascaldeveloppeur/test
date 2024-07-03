create table "ecommerce-user" (
        "id" bigint not null,
        "name" varchar(20),
        "email" varchar(255) not null unique,
        "password" varchar(255) unique,
        primary key ("id")
    );
create sequence "ecommerce-user_seq" start with 1 increment by 1

 