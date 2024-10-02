   create table "product" (
        "category_id" bigint,
        "created_at" timestamp(6),
        "id" bigint not null,
        "price_id" bigint unique,
        "stock_id" bigint unique,
        "updated_at" timestamp(6),
        "description" varchar(100),
        "name" varchar(255),
        "sku" varchar(255),
        primary key ("id")
    );
create sequence "product_seq" start with 1 increment by 1