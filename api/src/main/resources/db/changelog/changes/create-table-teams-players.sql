--liquibase formatted sql
--changeset <litvik>:<create-table-teams-players>
CREATE TABLE IF NOT EXISTS teams_players
(
    team_id bigint not null,
    player_id bigint not null,
    CONSTRAINT teams_players_pk PRIMARY KEY (team_id, player_id),
    CONSTRAINT fk_teams_players_team FOREIGN KEY (team_id) REFERENCES teams (id),
    CONSTRAINT fk_teams_players_player FOREIGN KEY (player_id) REFERENCES players (id)
    );
--rollback DROP TABLE teams-players;