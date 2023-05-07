package stracture.football.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import stracture.football.dto.TeamRequestDto;
import stracture.football.dto.TeamResponseDto;
import stracture.football.dto.mapper.TeamMapper;
import stracture.football.model.Team;
import stracture.football.service.PlayerService;
import stracture.football.service.TeamService;

import java.math.BigDecimal;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TeamControllerTest {
    @MockBean
    private TeamService teamService;
    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void shouldSaveTeam() {
        Team team = new Team(null, "Barsa",
                "Spain", "Barcelona", BigDecimal.valueOf(300000),
                BigDecimal.valueOf(6), Collections.emptyList());

        Mockito.when(teamService.save(team))
                .thenReturn(new Team(3L, "Barsa", "Spain", "Barcelona",
                        BigDecimal.valueOf(300000), BigDecimal.valueOf(6), Collections.emptyList()));

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new TeamRequestDto(team.getTitle(), team.getCountry(),
                        team.getCity(), team.getBalance(), team.getCommission()))
                .when()
                .post("/teams")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(3))
                .body("title", Matchers.equalTo("Barsa"))
                .body("country", Matchers.equalTo("Spain"))
                .body("city", Matchers.equalTo("Barcelona"))
                .body("balance", Matchers.equalTo(300000))
                .body("commission", Matchers.equalTo(6));
    }
}