--liquibase formatted sql
--changeset <litvik>:<create-table-players>
CREATE TABLE IF NOT EXISTS players
(
    id bigint auto_increment,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    birth_date date not null,
    start_career date not null,
    team_id bigint,
    CONSTRAINT players_pk PRIMARY KEY (id),
    CONSTRAINT fk_team_id FOREIGN KEY (team_id) REFERENCES teams(id)
    );
--rollback DROP TABLE players;