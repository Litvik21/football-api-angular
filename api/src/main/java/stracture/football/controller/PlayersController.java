package stracture.football.controller;

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
    public PlayerResponseDto save(@RequestBody PlayerRequestDto dto) {
        Player player = playerMapper.toModel(dto);
        return playerMapper.toDto(service.save(player));
    }

    @PutMapping("/{id}")
    public PlayerResponseDto update(@RequestBody PlayerRequestDto dto,
                                    @PathVariable Long id) {
        Player player = playerMapper.toModel(dto);
        player.setId(id);
        return playerMapper.toDto(service.save(player));
    }

    @GetMapping("/get/{id}")
    public PlayerResponseDto get(@PathVariable Long id) {
        Player player = service.get(id);
        return playerMapper.toDto(player);
    }

    @GetMapping
    public List<PlayerResponseDto> getAll() {
        return service.getAll().stream()
                .map(playerMapper::toDto)
                .toList();
    }

    @DeleteMapping("/remove/{id}")
    public boolean remove(@PathVariable Long id) {
        return service.delete(id);
    }

    @PutMapping("/transfer/{id}")
    public boolean transfer(@PathVariable Long id,
                            @RequestParam("teamId")
                            Long newTeamId) {
        return service.transfer(id, newTeamId);
    }
}
