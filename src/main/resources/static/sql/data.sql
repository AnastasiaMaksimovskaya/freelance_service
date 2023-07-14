create table users
(
    user_id  varchar   not null
        constraint user_pk
            primary key,
    created  timestamp not null,
    updated  varchar   not null,
    login    varchar   not null,
    password varchar   not null
);

create table public.client
(
    client_id   varchar   not null
        constraint client_pk
            primary key,
    user_id     varchar   not null
        constraint client_user_fk
            references public."user",
    updated     timestamp not null,
    created     timestamp not null,
    first_name  varchar   not null,
    middle_name varchar,
    last_name   varchar,
    email       varchar   not null,
    phone       varchar   not null
);

create table public.performer
(
    performer_id varchar   not null
        constraint performer_pk
            primary key,
    user_id      varchar   not null
        constraint performer_user_fk
            references public."user",
    updated      timestamp not null,
    created      timestamp not null,
    first_name   varchar   not null,
    middle_name  varchar,
    last_name    varchar,
    email        varchar   not null,
    phone        varchar   not null,
    rate         real
);

create table order
(
    order_id  varchar   not null
        constraint order_pk
            primary key,
    updated   timestamp not null,
    created   timestamp not null,
    client_id varchar   not null
        constraint order_client_client_id_fk
            references client,
    currency  varchar,
    name      varchar   not null,
    price     integer,
    theme     varchar   not null
);

