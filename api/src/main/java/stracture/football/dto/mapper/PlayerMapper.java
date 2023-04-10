package stracture.football.dto.mapper;

import org.springframework.stereotype.Component;
import stracture.football.dto.PlayerRequestDto;
import stracture.football.dto.PlayerResponseDto;
import stracture.football.model.Player;
import stracture.football.model.Team;
import stracture.football.service.TeamService;

@Component
public class PlayerMapper {
    private final TeamService teamService;

    public PlayerMapper(TeamService teamService) {
        this.teamService = teamService;
    }

    public Player toModel(PlayerRequestDto dto) {
        Player player = new Player();
        player.setFirstName(dto.firstName());
        player.setLastName(dto.lastName());
        player.setBirthDate(dto.birthDate());
        player.setStartCareer(dto.startCareer());
        player.setTeam(teamService.get(dto.teamId()));

        return player;
    }

    public PlayerResponseDto toDto(Player player) {
        return new PlayerResponseDto(
                player.getId(),
                player.getFirstName(),
                player.getLastName(),
                player.getBirthDate(),
                player.getStartCareer(),
                player.getTeam().getId()
        );
    }
}
