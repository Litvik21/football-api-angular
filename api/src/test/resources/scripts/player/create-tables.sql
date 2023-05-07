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

INSERT INTO teams (title, country, city, balance, commission) VALUES
                                                                  ('Bayern Munich', 'Germany', 'Munich', 6005000, 8.4),
                                                                  ('AC Milan', 'Italy', 'Milan', 40000000, 7.3);