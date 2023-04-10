package stracture.football.dto;

import java.time.LocalDate;

public record PlayerResponseDto(Long id,
                                String firstName,
                                String lastName,
                                LocalDate birthDate,
                                LocalDate startCareer,
                                Long teamId) {
}
