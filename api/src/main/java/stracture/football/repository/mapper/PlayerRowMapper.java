package stracture.football.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import stracture.football.model.Player;
import stracture.football.service.TeamService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PlayerRowMapper implements RowMapper<Player> {
    private final TeamService teamService;

    public PlayerRowMapper(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
        Player player = new Player();
        player.setId(rs.getLong("id"));
        player.setFirstName(rs.getString("first_name"));
        player.setLastName(rs.getString("last_name"));
        player.setBirthDate(rs.getObject("birth_date", LocalDate.class));
        player.setStartCareer(rs.getObject("start_career", LocalDate.class));

        Long teamId = rs.getLong("team_id");
        if (teamId != null) {
            player.setTeam(teamService.get(teamId));
        }

        return player;
    }
}