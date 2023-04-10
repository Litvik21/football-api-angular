package stracture.football.dto;

import java.math.BigDecimal;

public record TeamRequestDto(String title,
                             String country,
                             String city,
                             BigDecimal balance,
                             Double commission) {
}
