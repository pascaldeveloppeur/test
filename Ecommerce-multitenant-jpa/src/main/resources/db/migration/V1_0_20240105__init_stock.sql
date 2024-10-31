 create table "stocks" (
        "quantity" integer not null,
        "id" bigint not null,
        "last_updated" timestamp(6),
        primary key ("id")
    );
  create sequence "stocks_id_seq" start with 1 increment by 1