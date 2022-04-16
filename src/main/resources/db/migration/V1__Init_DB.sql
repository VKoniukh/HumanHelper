create table home(
    id          BIGSERIAL primary key,
    description varchar(2048),
    location    varchar(2048),
    price       numeric(19, 2),
    sleep_place int4 not null
)