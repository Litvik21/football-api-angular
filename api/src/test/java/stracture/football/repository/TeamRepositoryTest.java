package stracture.football.repository;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import stracture.football.model.Team;
import stracture.football.repository.impl.TeamRepositoryImpl;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = "spring.liquibase.enabled=false")
public class TeamRepositoryTest {
    @Autowired
    private TeamRepositoryImpl teamRepository;


    @Test
    @Sql("/scripts/team/create-table-team.sql")
    @Sql("/scripts/team/clear-data-base.sql")
    public void shouldSaveTeam() {
        Team team = new Team();
        team.setTitle("Barsa");
        team.setCountry("Spain");
        team.setCity("Barcelona");
        team.setBalance(BigDecimal.valueOf(300000));
        team.setCommission(BigDecimal.valueOf(6));

        Team actual = teamRepository.save(team);

        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals(team.getTitle(), actual.getTitle());
        Assertions.assertEquals(team.getCountry(), actual.getCountry());
        Assertions.assertEquals(team.getCity(), actual.getCity());
        Assertions.assertEquals(team.getBalance(), actual.getBalance());
        Assertions.assertEquals(team.getCommission(), actual.getCommission());
    }

    @Test
    @Sql("/scripts/team/create-table-team.sql")
    @Sql("/scripts/team/clear-data-base.sql")
    @Sql("/scripts/team/init-two-teams.sql")
    public void shouldUpdateTeam() {
        Optional<Team> teamBeforeUpdates = teamRepository.findById(2L);

        Team team = new Team();
        team.setId(2L);
        team.setTitle("Barsa");
        team.setCountry("Spain");
        team.setCity("Barcelona");
        team.setBalance(BigDecimal.valueOf(300000));
        team.setCommission(BigDecimal.valueOf(6));

        Team actual = teamRepository.update(team);

        Assertions.assertEquals(teamBeforeUpdates.get().getId(), actual.getId());
        Assertions.assertNotEquals(teamBeforeUpdates.get().getTitle(), actual.getTitle());
        Assertions.assertEquals(team.getTitle(), actual.getTitle());
    }

    @Test
    @Sql("/scripts/team/create-table-team.sql")
    @Sql("/scripts/team/clear-data-base.sql")
    @Sql("/scripts/team/init-two-teams.sql")
    public void shouldGetTeam() {
        Optional<Team> actual = teamRepository.findById(1L);

        Assertions.assertEquals(1L, actual.get().getId());
        Assertions.assertEquals("Bayern Munich", actual.get().getTitle());
    }

    @Test
    @Sql("/scripts/team/create-table-team.sql")
    @Sql("/scripts/team/clear-data-base.sql")
    @Sql("/scripts/team/init-two-teams.sql")
    public void shouldGetListOfTeams() {
        List<Team> actual = teamRepository.findAll();

        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals("Bayern Munich", actual.get(0).getTitle());
        Assertions.assertEquals("AC Milan", actual.get(1).getTitle());
    }

    @Test
    @Sql("/scripts/team/create-table-team.sql")
    @Sql("/scripts/team/clear-data-base.sql")
    @Sql("/scripts/team/init-two-teams.sql")
    public void shouldDeleteTeam() {
        int sizeBeforeDeleting = teamRepository.findAll().size();
        boolean actual = teamRepository.delete(1L);
        int sizeAfterDeleting = teamRepository.findAll().size();

        Assertions.assertTrue(actual);
        Assertions.assertEquals(2, sizeBeforeDeleting);
        Assertions.assertEquals(1, sizeAfterDeleting);
    }

    @Test
    @Sql("/scripts/team/create-table-team.sql")
    @Sql("/scripts/team/clear-data-base.sql")
    @Sql("/scripts/team/init-two-teams.sql")
    public void shouldUpdateBalanceOfTeams() {
        boolean actual = teamRepository
                .updatedBalances(1L, 2L, BigDecimal.valueOf(100), BigDecimal.valueOf(200));
        BigDecimal firstTeamBalanceAfterUpdating = teamRepository.findById(1L).get()
                .getBalance().setScale(0, RoundingMode.DOWN);
        BigDecimal secondTeamBalanceAfterUpdating = teamRepository.findById(2L).get()
                .getBalance().setScale(0, RoundingMode.DOWN);

        Assertions.assertTrue(actual);
        Assertions.assertEquals(BigDecimal.valueOf(100), firstTeamBalanceAfterUpdating);
        Assertions.assertEquals(BigDecimal.valueOf(200), secondTeamBalanceAfterUpdating);
    }
}