create table "categories" (
        "id" bigint not null,
        "description" varchar(255),
        "name" varchar(255),
        primary key ("id")
    );
create sequence "category_seq" start with 1 increment by 1