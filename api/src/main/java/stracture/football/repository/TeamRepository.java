package stracture.football.repository;

import stracture.football.model.Team;

import java.util.List;
import java.util.Optional;

public interface TeamRepository  {
    Team save(Team team);

    Team update(Team team);

    Optional<Team> findById(Long id);

    List<Team> findAll();

    void delete(Long id);
}
