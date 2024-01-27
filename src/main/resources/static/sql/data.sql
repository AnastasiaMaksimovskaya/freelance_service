create table public.client
(
    client_id   varchar   not null
        constraint client_pk
            primary key,
    updated     timestamp not null,
    created     timestamp not null,
    first_name  varchar   not null,
    middle_name varchar,
    last_name   varchar,
    email       varchar   not null,
    phone       varchar   not null,
    login    varchar   not null,
    password varchar   not null
);

create table public.performer
(
    performer_id varchar   not null
        constraint performer_pk
            primary key,
    updated      timestamp not null,
    created      timestamp not null,
    first_name   varchar   not null,
    middle_name  varchar,
    last_name    varchar,
    email        varchar   not null,
    phone        varchar   not null,
    rate         real,
    login    varchar   not null,
    password varchar   not null
);

create table orders
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

create table exchange
(
    currency varchar not null
        constraint exchange_pk
            primary key,
    units  double precision not null
);
create table temp_user
(
    user_id          varchar   not null
        constraint temp_user_pk
            primary key,
    confirmation_key varchar   not null,
    expires_at       timestamp not null,
    password         varchar   not null,
    first_name       varchar   not null,
    last_name        varchar   not null,
    login            varchar   not null,
    role             varchar   not null,
    phone            integer   not null,
    middle_name      varchar
);