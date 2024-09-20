create table "ecommerce-user" (
        "enabled" boolean not null,
        "created_at" timestamp(6),
        "id" bigint not null,
        "updated_at" timestamp(6),
        "username" varchar(20),
        "email" varchar(255) not null unique,
        "first_name" varchar(255),
        "last_name" varchar(255),
        "password" varchar(255) unique,
        "role" varchar(255) check ("role" in ('ADMIN','CLIENT')),
        primary key ("id")
    );
create sequence "ecommerce-user_seq" start with 1 increment by 1

