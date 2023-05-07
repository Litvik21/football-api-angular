package stracture.football.service;

import stracture.football.model.Player;
import java.util.List;

public interface PlayerService {
    Player save(Player player);

    Player update(Player player);

    List<Player> getAll();

    Player get(Long id);

    boolean delete(Long id);

    boolean transfer(Long playerId, Long newTeamId);

    List<Player> findPlayersByTeamId(Long teamId);
}
