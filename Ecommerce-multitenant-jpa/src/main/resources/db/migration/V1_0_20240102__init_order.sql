create table "order" (
        "total_amount" float(53),
        "id" bigint not null,
        "user_id" bigint,
        primary key ("id")
    );
create sequence "order_seq" start with 1 increment by 1