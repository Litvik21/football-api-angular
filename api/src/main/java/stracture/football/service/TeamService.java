package stracture.football.service;

import stracture.football.model.Team;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TeamService {
    Team save(Team team);

    Team update(Team team);

    List<Team> getAll();

    Team get(Long id);

    boolean delete(Long id);

    boolean updatedBalances(Long id1, Long id2,
                            BigDecimal balance1, BigDecimal balance2);
}
