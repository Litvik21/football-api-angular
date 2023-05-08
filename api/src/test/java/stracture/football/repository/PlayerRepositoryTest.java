package stracture.football.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import stracture.football.model.Player;
import stracture.football.model.Team;
import stracture.football.repository.impl.PlayerRepositoryImpl;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = "spring.liquibase.enabled=false")
@Sql("/scripts/player/create-tables.sql")
public class PlayerRepositoryTest {
    @Autowired
    private PlayerRepositoryImpl playerRepository;

    @Test
    @Sql("/scripts/player/clear-data-base.sql")
    public void shouldSavePlayer() {
        Team team = new Team();
        team.setId(2L);

        Player player = new Player();
        player.setFirstName("John");
        player.setLastName("Doe");
        player.setBirthDate(LocalDate.of(1990, 1, 1));
        player.setStartCareer(LocalDate.of(2010, 1, 1));
        player.setTeam(team);
        Player actual = playerRepository.save(player);

        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals(player.getFirstName(), actual.getFirstName());
        Assertions.assertEquals(player.getLastName(), actual.getLastName());
        Assertions.assertEquals(player.getBirthDate(), actual.getBirthDate());
        Assertions.assertEquals(player.getStartCareer(), actual.getStartCareer());
    }

    @Test
    @Sql("/scripts/player/clear-data-base.sql")
    @Sql("/scripts/player/init_four_players.sql")
    public void shouldGetPlayer() {
        Optional<Player> actual = playerRepository.findById(1L);

        Assertions.assertEquals(1L, actual.get().getId());
        Assertions.assertEquals("Zlatan", actual.get().getFirstName());
    }

    @Test
    @Sql("/scripts/player/create-tables.sql")
    @Sql("/scripts/player/init_four_players.sql")
    public void shouldGetListOfPlayers() {
        List<Player> actual = playerRepository.findAll();

        Assertions.assertEquals(4, actual.size());
        Assertions.assertEquals("Zlatan", actual.get(0).getFirstName());
        Assertions.assertEquals("Neymar", actual.get(1).getFirstName());
        Assertions.assertEquals("Andres", actual.get(2).getFirstName());
        Assertions.assertEquals("Thierry", actual.get(3).getFirstName());
    }

    @Test
    @Sql("/scripts/player/clear-data-base.sql")
    @Sql("/scripts/player/init_four_players.sql")
    public void shouldUpdatePlayer() {
        Optional<Player> playerInDb = playerRepository.findById(1L);

        Team team = new Team();
        team.setId(2L);

        Player updatedPlayer = new Player();
        updatedPlayer.setId(1L);
        updatedPlayer.setFirstName("Nazar");
        updatedPlayer.setLastName("Litvik");
        updatedPlayer.setBirthDate(LocalDate.of(1990, 1, 1));
        updatedPlayer.setStartCareer(LocalDate.of(2010, 1, 1));
        updatedPlayer.setTeam(team);
        Player actual = playerRepository.update(updatedPlayer);

        Assertions.assertEquals(playerInDb.get().getId(), actual.getId());
        Assertions.assertNotEquals(playerInDb.get().getFirstName(), actual.getFirstName());
        Assertions.assertEquals(updatedPlayer.getFirstName(), actual.getFirstName());
    }

    @Test
    @Sql("/scripts/player/clear-data-base.sql")
    @Sql("/scripts/player/init_four_players.sql")
    public void shouldDeletePlayer() {
        int beforeDeleting = playerRepository.findAll().size();
        boolean actual = playerRepository.delete(4L);
        int afterDeleting = playerRepository.findAll().size();

        Assertions.assertTrue(actual);
        Assertions.assertEquals(4, beforeDeleting);
        Assertions.assertEquals(3, afterDeleting);
    }

    @Test
    @Sql("/scripts/player/clear-data-base.sql")
    @Sql("/scripts/player/init_four_players.sql")
    public void shouldGetPlayersOfChosenTeam() {
        int allPlayersInDb = playerRepository.findAll().size();
        List<Player> actual = playerRepository.findPlayersByTeamId(1L);

        Assertions.assertEquals(4, allPlayersInDb);
        Assertions.assertEquals(3, actual.size());
    }

    @Test
    @Sql("/scripts/player/clear-data-base.sql")
    @Sql("/scripts/player/init_four_players.sql")
    public void shouldUpdateTeamOfPlayer() {
        int beforeUpdatingCountPlayersOfSecondTeam = playerRepository.findPlayersByTeamId(2L).size();
        boolean actual = playerRepository.updatePlayerTeamId(1L, 2L);
        int afterUpdatingCountPlayersOfSecondTeam = playerRepository.findPlayersByTeamId(2L).size();

        Assertions.assertEquals(1, beforeUpdatingCountPlayersOfSecondTeam);
        Assertions.assertTrue(actual);
        Assertions.assertEquals(2, afterUpdatingCountPlayersOfSecondTeam);
    }
}