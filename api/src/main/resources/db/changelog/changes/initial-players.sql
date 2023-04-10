--liquibase formatted sql
--changeset <litvik>:<insert-initial-players>
INSERT INTO players (first_name, last_name, birth_date, start_career, team_id) VALUES
                                                                               ('Zlatan', 'Ibrahimovic', '1981-10-03', '1999-09-01', 1),
                                                                               ('Neymar', 'Junior', '1992-02-05', '2009-01-01', 1),
                                                                               ('Andres', 'Iniesta', '1984-05-11', '2001-09-01', 1),
                                                                               ('Thierry', 'Henry', '1977-08-17', '1994-01-01', 1),
                                                                               ('Steven', 'Gerrard', '1980-05-30', '1998-11-29', 1),
                                                                               ('Cristiano', 'Ronaldo', '1985-02-05', '2002-08-14', 1),
                                                                               ('Lionel', 'Messi', '1987-06-24', '2003-10-16', 2),
                                                                               ('David', 'Beckham', '1975-05-02', '1992-09-23', 2),
                                                                               ('Robert', 'Lewandowski', '1988-08-21', '2006-09-19', 2),
                                                                               ('Diego', 'Maradona', '1960-10-30', '1976-10-20', 2),
                                                                               ('Paolo', 'Maldini', '1968-06-26', '1985-01-20', 2);