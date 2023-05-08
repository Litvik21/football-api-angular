package stracture.football.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import stracture.football.model.Team;
import stracture.football.repository.TeamRepository;
import stracture.football.repository.mapper.TeamRowMapper;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class TeamRepositoryImpl implements TeamRepository {
    private static final String SQL_SAVE =
            "INSERT INTO teams (title, country, city, balance, commission) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_FIND_ALL =
            "SELECT * FROM teams";
    private static final String SQL_FIND_BY_ID =
            "SELECT * FROM teams WHERE id = ?";
    private static final String SQL_UPDATE =
            "UPDATE teams SET title = ?, country = ?, city = ?, balance = ?, commission = ? WHERE id = ?";
    private static final String SQL_DELETE =
            "DELETE FROM teams WHERE id = ?";
    private static final String SQL_UPDATE_BALANCES =
            "UPDATE teams SET balance = CASE id WHEN ? THEN ? WHEN ? THEN ? ELSE balance END WHERE id IN (?, ?)";

    private final JdbcTemplate jdbcTemplate;
    private final TeamRowMapper teamRowMapper;

    public TeamRepositoryImpl(JdbcTemplate jdbcTemplate,
                              TeamRowMapper teamRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.teamRowMapper = teamRowMapper;
    }

    @Override
    public Team save(Team team) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(SQL_SAVE, new String[]{"id"});
            ps.setString(1, team.getTitle());
            ps.setString(2, team.getCountry());
            ps.setString(3, team.getCity());
            ps.setBigDecimal(4, team.getBalance());
            ps.setBigDecimal(5, team.getCommission());
            return ps;
        }, keyHolder);
        team.setId(keyHolder.getKey().longValue());
        return team;
    }

    @Override
    public Optional<Team> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id}, teamRowMapper));
    }

    @Override
    public List<Team> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, teamRowMapper);
    }

    @Override
    public Team update(Team team) {
        jdbcTemplate.update(SQL_UPDATE,
                team.getTitle(),
                team.getCountry(),
                team.getCity(),
                team.getBalance(),
                team.getCommission(),
                team.getId());
        return team;
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
    public boolean updatedBalances(Long id1, Long id2, BigDecimal balance1, BigDecimal balance2) {
        return jdbcTemplate.update(SQL_UPDATE_BALANCES, id1, balance1, id2, balance2, id1, id2) > 0;
    }
}
