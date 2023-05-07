CREATE TABLE IF NOT EXISTS teams
(
    id bigint auto_increment,
    title varchar(255) not null,
    country varchar(255) not null,
    city varchar(255) not null,
    balance decimal(19,2) not null,
    commission decimal(19,2) not null,
    CONSTRAINT teams_pk PRIMARY KEY (id)
    );