package stracture.football.service.impl;

import stracture.football.model.Team;
import stracture.football.repository.TeamRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import stracture.football.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository repository;

    public TeamServiceImpl(TeamRepository repository) {
        this.repository = repository;
    }

    @Override
    public Team save(Team team) {
        return repository.save(team);
    }

    @Override
    public Team update(Team team) {
        return repository.update(team);
    }

    @Override
    public List<Team> getAll() {
        return repository.findAll();
    }

    @Override
    public Team get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Can't find team by id: " + id));
    }

    @Override
    public boolean delete(Long id) {
        repository.delete(id);
        return repository.findById(id).isEmpty();
    }
}
