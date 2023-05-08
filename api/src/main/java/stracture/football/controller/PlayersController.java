package stracture.football.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import stracture.football.dto.PlayerRequestDto;
import stracture.football.dto.PlayerResponseDto;
import stracture.football.dto.mapper.PlayerMapper;
import stracture.football.model.Player;
import stracture.football.service.PlayerService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
public class PlayersController {
    private final PlayerMapper playerMapper;
    private final PlayerService service;

    public PlayersController(PlayerMapper playerMapper,
                             PlayerService service) {
        this.playerMapper = playerMapper;
        this.service = service;
    }

    @PostMapping
    @ApiOperation(value = "Save player to DB")
    public PlayerResponseDto save(@RequestBody PlayerRequestDto dto) {
        Player player = playerMapper.toModel(dto);
        return playerMapper.toDto(service.save(player));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update player")
    public PlayerResponseDto update(@RequestBody PlayerRequestDto dto,
                                    @ApiParam(value = "Id of player that you want to update")
                                    @PathVariable Long id) {
        Player player = playerMapper.toModel(dto);
        player.setId(id);
        return playerMapper.toDto(service.update(player));
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "Getting player")
    public PlayerResponseDto get(@ApiParam(value = "Id of player that you want to get")
            @PathVariable Long id) {
        Player player = service.get(id);
        return playerMapper.toDto(player);
    }

    @GetMapping
    @ApiOperation(value = "Getting players list")
    public List<PlayerResponseDto> getAll() {
        return service.getAll().stream()
                .map(playerMapper::toDto)
                .toList();
    }

    @DeleteMapping("/remove/{id}")
    @ApiOperation(value = "Removing player")
    public boolean remove(@ApiParam(value = "Id of player that you want to remove")
            @PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping("/transfer/{id}")
    @ApiOperation(value = "Transfer player")
    public boolean transfer(@ApiParam(value = "Id of player that you want to transfer")
                                @PathVariable Long id,
                            @ApiParam(value = "Id of new team of player")
                            @RequestParam("teamId")
                            Long newTeamId) {
        return service.transfer(id, newTeamId);
    }
}
