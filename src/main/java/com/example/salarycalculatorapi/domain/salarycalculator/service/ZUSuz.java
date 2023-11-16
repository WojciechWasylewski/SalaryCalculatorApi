package com.example.salarycalculatorapi.domain.salarycalculator.service;

import java.math.BigDecimal;

public enum ZUSuz {

    EMERYTALNA(BigDecimal.valueOf(0.0976)),
    RENTOWA(BigDecimal.valueOf(0.015)),
    CHOROBOWA(BigDecimal.valueOf(0)),
    ZDROWOTNA(BigDecimal.valueOf(0.09));

    private final BigDecimal rate;

    ZUSuz(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
