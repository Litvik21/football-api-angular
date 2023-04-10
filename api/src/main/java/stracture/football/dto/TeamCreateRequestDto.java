package stracture.football.dto;

import java.math.BigDecimal;

public record TeamCreateRequestDto(String title,
                                   String country,
                                   String city,
                                   BigDecimal balance,
                                   Double commission) {
}
