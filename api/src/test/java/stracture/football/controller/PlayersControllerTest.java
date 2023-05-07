package stracture.football.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import stracture.football.dto.PlayerRequestDto;
import stracture.football.model.Player;
import stracture.football.model.Team;
import stracture.football.service.PlayerService;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class PlayersControllerTest {
    @MockBean
    private PlayerService playerService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void shouldSavePlayer() {
        Team team = new Team();
        team.setId(1L);
        Player player = new Player(null, "Nazar", "Litvik",
                LocalDate.of(2001, 11, 2), LocalDate.of(2016, 1, 4), team);

        Mockito.when(playerService.save(player))
                .thenReturn(new Player(12L, "Nazar", "Litvik",
                        LocalDate.of(2001, 11, 2), LocalDate.of(2016, 1, 4), team));

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new PlayerRequestDto(player.getFirstName(), player.getLastName(),
                        player.getBirthDate(), player.getStartCareer(), player.getTeam().getId()))
                .when()
                .post("/players")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(12))
                .body("firstName", Matchers.equalTo("Nazar"))
                .body("lastName", Matchers.equalTo("Litvik"));
    }
}