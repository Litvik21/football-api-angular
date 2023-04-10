--liquibase formatted sql
--changeset <litvik>:<insert-initial-teams>
INSERT INTO teams (title, country, city, balance, commission) VALUES
('Bayern Munich', 'Germany', 'Munich', 600000, 8.4),
('AC Milan', 'Italy', 'Milan', 400000, 7.3);