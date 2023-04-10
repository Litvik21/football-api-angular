package stracture.football.repository;

import stracture.football.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    Player save(Player player);

    Optional<Player> findById(Long id);

    List<Player> findAll();

    List<Player> findAllByIds(List<Long> playerIds);

    Player update(Player player);

    boolean delete(Long id);

    List<Player> findPlayersByTeamId(Long teamId);

    boolean updatePlayerTeamId(Long playerId, Long teamId);
}
