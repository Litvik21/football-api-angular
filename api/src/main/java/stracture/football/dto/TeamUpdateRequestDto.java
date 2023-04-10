package stracture.football.dto;

import stracture.football.model.Player;

import java.math.BigDecimal;
import java.util.List;

public record TeamUpdateRequestDto(String title,
                                   String country,
                                   String city,
                                   BigDecimal balance,
                                   Double commission,
                                   List<Long> playerIds) {
}
