DELETE FROM players;
ALTER TABLE players ALTER COLUMN id RESTART WITH 1;