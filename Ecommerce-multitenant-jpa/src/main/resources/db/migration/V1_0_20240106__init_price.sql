 create table "prices" (
        "amount" numeric(38,2),
        "discount" numeric(38,2),
        "end_date" timestamp(6),
        "id" bigint not null,
        "start_date" timestamp(6),
        primary key ("id")
    );
create sequence "prices_id_seq" start with 1 increment by 1