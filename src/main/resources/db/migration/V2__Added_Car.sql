create table car
(
    id          BIGSERIAL primary key,
    description varchar(2048),
    route_from   varchar(255) not null,
    route_to     varchar(255) not null,
    date        date not null,
    price       numeric(19, 2)
)