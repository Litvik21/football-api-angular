package stracture.football.repository.mapper;

import stracture.football.model.Team;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class TeamRowMapper implements RowMapper<Team> {
    @Override
    public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
        Team team = new Team();
        team.setId(rs.getLong("id"));
        team.setTitle(rs.getString("title"));
        team.setCountry(rs.getString("country"));
        team.setCity(rs.getString("city"));
        team.setBalance(rs.getBigDecimal("balance"));
        team.setCommission(rs.getDouble("commission"));
        return team;
    }
}