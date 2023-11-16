package com.example.salarycalculatorapi.domain.salarycalculator.service;

import java.math.BigDecimal;

public enum ZUSUoP {
    EMERYTALNA(BigDecimal.valueOf(0.0976)),
    RENTOWA(BigDecimal.valueOf(0.015)),
    CHOROBOWA(BigDecimal.valueOf(0.0245)),
    ZDROWOTNA(BigDecimal.valueOf(0.09));

    private final BigDecimal rate;

    ZUSUoP(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
