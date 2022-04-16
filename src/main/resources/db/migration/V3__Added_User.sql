create table usr
(
    id       varchar(255) primary key,
    username varchar(255),
    email    varchar(255) not null,
    provider varchar(255)
)