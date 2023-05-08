package stracture.football.dto.mapper;

import stracture.football.dto.TeamRequestDto;
import stracture.football.dto.TeamResponseDto;
import stracture.football.model.Player;
import stracture.football.model.Team;
import stracture.football.service.PlayerService;
import org.springframework.stereotype.Component;

@Component
public class TeamMapper {
    private final PlayerService playerService;

    public TeamMapper(PlayerService playerService) {
        this.playerService = playerService;
    }

    public Team toModel(TeamRequestDto dto) {
        Team team = new Team();
        team.setTitle(dto.title());
        team.setCountry(dto.country());
        team.setCity(dto.city());
        team.setBalance(dto.balance());
        team.setCommission(dto.commission());

        return team;
    }

    public TeamResponseDto toDto(Team team) {
        team.setPlayers(playerService.findPlayersByTeamId(team.getId()));
        return new TeamResponseDto(
                team.getId(),
                team.getTitle(),
                team.getCountry(),
                team.getCity(),
                team.getBalance(),
                team.getCommission(),
                team.getPlayers().stream().map(Player::getId).toList()
        );
    }
}
