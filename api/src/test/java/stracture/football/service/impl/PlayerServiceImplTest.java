package stracture.football.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import stracture.football.model.Player;
import stracture.football.model.Team;
import stracture.football.repository.impl.PlayerRepositoryImpl;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {
    @InjectMocks
    private PlayerServiceImpl playerService;
    @Mock
    PlayerRepositoryImpl playerRepository;
    @Mock
    private TeamServiceImpl teamService;
    private Team currentTeam;
    private Team futureTeam;
    private Player player;

    @BeforeEach
    void setUp() {
        currentTeam = new Team();
        currentTeam.setId(1L);
        currentTeam.setTitle("Barsa");
        currentTeam.setCountry("Caraganda");
        currentTeam.setCity("Crasty");
        currentTeam.setBalance(BigDecimal.valueOf(190000));
        currentTeam.setCommission(BigDecimal.valueOf(7));

        futureTeam = new Team();
        futureTeam.setId(2L);
        futureTeam.setTitle("Marsa");
        futureTeam.setCountry("Finland");
        futureTeam.setCity("Frozy");
        futureTeam.setBalance(BigDecimal.valueOf(2201900));
        futureTeam.setCommission(BigDecimal.valueOf(8));

        player = new Player();
        player.setId(1L);
        player.setFirstName("Brand");
        player.setLastName("Smith");
        player.setBirthDate(LocalDate.of(1990,10,2));
        player.setStartCareer(LocalDate.of(2001,9,22));
        player.setTeam(currentTeam);
    }

    @Test
    public void testTransfer() {
        Mockito.when(playerRepository.findById(player.getId())).thenReturn(Optional.of(player));
        Mockito.when(teamService.get(futureTeam.getId())).thenReturn(futureTeam);
        Mockito.when(teamService.updatedBalances(futureTeam.getId(), currentTeam.getId(), BigDecimal.valueOf(1319961.06), BigDecimal.valueOf(1071938.94)))
                .thenReturn(true);

        boolean actual = playerService.transfer(player.getId(), futureTeam.getId());

        Assertions.assertTrue(actual);
    }

    @Test
    public void testDoTransferOperation() {
        Mockito.when(teamService.updatedBalances(futureTeam.getId(), currentTeam.getId(), BigDecimal.valueOf(1319961.06), BigDecimal.valueOf(1071938.94)))
                .thenReturn(true);
        boolean actual = playerService.doTransferOperation(futureTeam.getId(), player, BigDecimal.valueOf(1319961.06), BigDecimal.valueOf(881938.94));

        verify(playerRepository).updatePlayerTeamId(player.getId(), futureTeam.getId());
        verify(teamService).updatedBalances(futureTeam.getId(), currentTeam.getId(), BigDecimal.valueOf(1319961.06), BigDecimal.valueOf(1071938.94));
        Assertions.assertTrue(actual);
    }

    @Test
    void shouldCalculateTotalCost() {
        BigDecimal actual = playerService.calculateTotalCost(player);
        Assertions.assertEquals(BigDecimal.valueOf(881938.94), actual);
    }

    @Test
    void shouldCalculateCostOfTransfer() {
        BigDecimal actual = playerService.calculateCostOfTransfer(player);
        Assertions.assertEquals(BigDecimal.valueOf(824242), actual);
    }
}