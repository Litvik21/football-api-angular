--liquibase formatted sql
--changeset <litvik>:<add-players-into-teams>
UPDATE teams t
SET t.players = (
    SELECT GROUP_CONCAT(tp.player_id SEPARATOR ',')
    FROM teams_players tp
    WHERE tp.team_id = t.id
);

