package stracture.football.repository.impl;

import org.springframework.jdbc.core.PreparedStatementCallback;
import stracture.football.model.Player;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import stracture.football.repository.PlayerRepository;
import stracture.football.repository.mapper.PlayerRowMapper;
import stracture.football.service.TeamService;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository {
    private static final String SQL_SAVE =
            "INSERT INTO players (first_name, last_name, birth_date, start_career, team_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_FIND_ALL =
            "SELECT * FROM players";
    private static final String SQL_FIND_BY_ID =
            "SELECT * FROM players WHERE id = ?";
    private static final String SQL_UPDATE =
            "UPDATE players SET first_name = ?, last_name = ?, birth_date = ?, start_career = ?, team_id = ? WHERE id = ?";
    private static final String SQL_DELETE =
            "DELETE FROM players WHERE id = ?";
    private static final String SQL_FIND_BY_TEAM_ID =
            "SELECT * FROM players WHERE team_id = ?";
    private static final String SQL_GET_ALL_BY_IDS =
            "SELECT * FROM Player WHERE id IN (?)";
    private static final String SQL_UPDATE_TEAM =
            "UPDATE players SET team_id = ? WHERE id = ?";

    private final JdbcTemplate jdbcTemplate;
    private final PlayerRowMapper playerRowMapper;

    public PlayerRepositoryImpl(JdbcTemplate jdbcTemplate,
                                PlayerRowMapper playerRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.playerRowMapper = playerRowMapper;
    }

    @Override
    public Player save(Player player) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE, new String[]{"id"});
            ps.setString(1, player.getFirstName());
            ps.setString(2, player.getLastName());
            ps.setObject(3, player.getBirthDate());
            ps.setObject(4, player.getStartCareer());
            ps.setLong(5, player.getTeam().getId());
            return ps;
        }, keyHolder);
        player.setId((Long) keyHolder.getKey());
        return player;
    }

    @Override
    public Optional<Player> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id}, playerRowMapper));
    }

    @Override
    public List<Player> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, playerRowMapper);
    }

    @Override
    public List<Player> findAllByIds(List<Long> playerIds) {
        return jdbcTemplate.query(SQL_GET_ALL_BY_IDS,  new Object[] { playerIds }, playerRowMapper);
    }

    @Override
    public Player update(Player player) {
        jdbcTemplate.update(SQL_UPDATE,
                player.getFirstName(),
                player.getLastName(),
                player.getBirthDate(),
                player.getStartCareer(),
                player.getTeam().getId(),
                player.getId());
        return player;
    }

    @Override
    public boolean delete(Long id) {
        return Boolean.TRUE.equals(jdbcTemplate.execute(SQL_DELETE, (PreparedStatementCallback<Boolean>) ps -> {
            ps.setLong(1, id);
            int rowsDeleted = ps.executeUpdate();
            return rowsDeleted > 0;
        }));
    }

    @Override
    public List<Player> findPlayersByTeamId(Long teamId) {
        return jdbcTemplate.query(SQL_FIND_BY_TEAM_ID, playerRowMapper, teamId);
    }

    @Override
    public boolean updatePlayerTeamId(Long playerId, Long teamId) {
        return jdbcTemplate.update(SQL_UPDATE_TEAM, teamId, playerId) > 0;
    }
}
