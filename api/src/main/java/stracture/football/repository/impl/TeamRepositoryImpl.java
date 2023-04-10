package stracture.football.repository.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import stracture.football.model.Team;
import stracture.football.repository.TeamRepository;
import stracture.football.repository.mapper.TeamRowMapper;
import stracture.football.service.PlayerService;

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

    private final JdbcTemplate jdbcTemplate;

    public TeamRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
            ps.setDouble(5, team.getCommission());
            return ps;
        }, keyHolder);
        team.setId((Long) keyHolder.getKey());
        return team;
    }

    @Override
    public Optional<Team> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id}, new TeamRowMapper()));
    }

    @Override
    public List<Team> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, new TeamRowMapper());
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
    public void delete(Long id) {
        jdbcTemplate.update(SQL_DELETE, id);
    }
}
