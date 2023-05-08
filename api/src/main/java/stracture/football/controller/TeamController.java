package stracture.football.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import stracture.football.dto.TeamRequestDto;
import stracture.football.dto.TeamResponseDto;
import stracture.football.dto.mapper.TeamMapper;
import stracture.football.model.Team;
import stracture.football.service.TeamService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamController {
    private final TeamMapper teamMapper;
    private final TeamService teamService;

    public TeamController(TeamMapper teamMapper, TeamService teamService) {
        this.teamMapper = teamMapper;
        this.teamService = teamService;
    }

    @PostMapping
    @ApiOperation(value = "Save team to DB")
    public TeamResponseDto save(@RequestBody TeamRequestDto dto) {
        Team team = teamService.save(teamMapper.toModel(dto));
        return teamMapper.toDto(team);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update team")
    public TeamResponseDto update(@RequestBody TeamRequestDto dto,
                                  @ApiParam(value = "Id of team that you want to update")
                                  @PathVariable Long id) {
        Team team = teamMapper.toModel(dto);
        team.setId(id);
        return teamMapper.toDto(teamService.update(team));
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "Getting team")
    public TeamResponseDto get(@ApiParam(value = "Id of team that you want to get")
            @PathVariable Long id) {
        return teamMapper.toDto(teamService.get(id));
    }

    @GetMapping
    @ApiOperation(value = "Getting teams list")
    public List<TeamResponseDto> getAll() {
        return teamService.getAll().stream()
                .map(teamMapper::toDto)
                .toList();
    }

    @DeleteMapping("/remove/{id}")
    @ApiOperation(value = "Removing team")
    public boolean remove(@ApiParam(value = "Id of team that you want to remove")
            @PathVariable Long id) {
        return teamService.delete(id);
    }
}
