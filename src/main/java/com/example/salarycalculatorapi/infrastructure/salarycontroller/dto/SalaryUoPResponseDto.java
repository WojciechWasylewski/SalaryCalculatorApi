package com.example.salarycalculatorapi.infrastructure.salarycontroller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record SalaryUoPResponseDto(
        @JsonProperty("Składka Emerytalna") BigDecimal skladkaEmerytalna,
        @JsonProperty("Składka Rentowa") BigDecimal skladkaRentowa,
        @JsonProperty("Składka Chorobowa") BigDecimal skladkaChorobowa,
        @JsonProperty("Składka Zdrowotna") BigDecimal skladkaZdrowotna,
        @JsonProperty("Zaliczka Na Podatek") BigDecimal zaliczkaNaPodatek,
        @JsonProperty("Koszt Pracownika") BigDecimal superBrutto,
        @JsonProperty("Wynagrodzenie Netto") BigDecimal salaryNetto
) {
}
