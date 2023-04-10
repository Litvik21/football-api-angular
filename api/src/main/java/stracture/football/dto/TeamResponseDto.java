package stracture.football.dto;

import java.math.BigDecimal;
import java.util.List;

public record TeamResponseDto(Long id,
                              String title,
                              String country,
                              String city,
                              BigDecimal balance,
                              Double commission,
                              List<Long> playerIds) {
}
