package com.example.salarycalculatorapi.domain.salarycalculator.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class UoDSalaryCalculator {


    private static final BigDecimal STALA_DLA_ZALICZKI_NA_PODATEK = BigDecimal.valueOf(0.096);


    public Map<String, BigDecimal> calculateUoD(BigDecimal salaryBrutto) {
        BigDecimal skladkaEmerytalna = BigDecimal.valueOf(0);
        BigDecimal skladkaRentowa = BigDecimal.valueOf(0);
        BigDecimal skladkaChorobowa = BigDecimal.valueOf(0);
        BigDecimal skladkaZdrowotna = BigDecimal.valueOf(0);
        BigDecimal zaliczkaNaPodatek = calculateZaliczkaNaDochodowy(salaryBrutto);
        BigDecimal superBrutto = salaryBrutto;
        BigDecimal salaryNetto = calculateNetSalary(salaryBrutto, zaliczkaNaPodatek);

        zaliczkaNaPodatek = zaliczkaNaPodatek.setScale(2, BigDecimal.ROUND_HALF_UP);
        superBrutto = superBrutto.setScale(2, BigDecimal.ROUND_HALF_UP);
        salaryNetto = salaryNetto.setScale(2, BigDecimal.ROUND_HALF_UP);


        Map<String, BigDecimal> result = new HashMap<>();
        result.put("Składka Emerytalna", skladkaEmerytalna);
        result.put("Składka Rentowa", skladkaRentowa);
        result.put("Składka Chorobowa", skladkaChorobowa);
        result.put("Składka Zdrowotna", skladkaZdrowotna);
        result.put("Zaliczka na podatek", zaliczkaNaPodatek);
        result.put("Wynagrodzenie netto", salaryNetto);
        result.put("Koszt pracownika", superBrutto);

        return result;
    }

    private BigDecimal calculateNetSalary(BigDecimal salaryBrutto, BigDecimal zaliczkaNaPodatek) {
        return salaryBrutto.subtract(zaliczkaNaPodatek);
    }

    private BigDecimal calculateZaliczkaNaDochodowy(BigDecimal salaryBrutto) {
        return salaryBrutto.multiply(STALA_DLA_ZALICZKI_NA_PODATEK);
    }

}
