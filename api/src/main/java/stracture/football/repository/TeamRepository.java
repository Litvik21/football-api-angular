package stracture.football.repository;

import stracture.football.model.Team;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TeamRepository  {
    Team save(Team team);

    Team update(Team team);

    Optional<Team> findById(Long id);

    List<Team> findAll();

    boolean delete(Long id);

    boolean updatedBalances(Long id1, Long id2,
                            BigDecimal balance1, BigDecimal balance2);
}
