package stracture.football.dto;

import java.time.LocalDate;

public record PlayerRequestDto(String firstName,
                               String lastName,
                               LocalDate birthDate,
                               LocalDate startCareer,
                               Long teamId) {}