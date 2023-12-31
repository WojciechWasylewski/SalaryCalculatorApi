package com.example.salarycalculatorapi.domain.salarycalculator.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;


@Service
public class UoPSalaryCalculator {
    private static final BigDecimal STAWKA_DOLNY_PROG_PODATKOWY = BigDecimal.valueOf(0.12);
    private static final BigDecimal KWOTA_ZMNIEJSZAJACA_PODATEK = BigDecimal.valueOf(300);
    private static final BigDecimal STALA_ROCZNA_DLA_DRUGIEGO_PROGU_PODATKOWEGO = BigDecimal.valueOf(10800);
    private static final BigDecimal NAJNIZSZA_KRAJOWA = BigDecimal.valueOf(3490);

    private static final BigDecimal STALA_ZUS = BigDecimal.valueOf(0.1371);
    private static final BigDecimal NUMBER_OF_MONTHS = BigDecimal.valueOf(12);
    private static final BigDecimal STAWKA_GORNY_PROG_PODATKOWY = BigDecimal.valueOf(0.32);
    private static final BigDecimal PROG_PODATKOWY = BigDecimal.valueOf(120000);
    private static final BigDecimal KWOTA_WOLNA_OD_PODATKU = BigDecimal.valueOf(30000);
    private static final BigDecimal STALA_SUPER_BRUTTO = BigDecimal.valueOf(1.2);


    public Map<String, BigDecimal> calculateUoP(BigDecimal salaryBrutto) {
        BigDecimal skladkaEmerytalna = calculatEmerytalna(salaryBrutto);
        BigDecimal skladkaRentowa = calculateRentowa(salaryBrutto);
        BigDecimal skladkaChorobowa = calculatChorobowa(salaryBrutto);
        BigDecimal skladkaZdrowotna = calculatZdrowotna(salaryBrutto);
        BigDecimal zaliczkaNaPodatek = calculateZaliczkaNaDochodowy(salaryBrutto);
        BigDecimal superBrutto = calculateEmployerCost(salaryBrutto);
        BigDecimal salaryNetto = calculateNetSalary(salaryBrutto, skladkaEmerytalna, skladkaRentowa, skladkaChorobowa, skladkaZdrowotna, zaliczkaNaPodatek);

        skladkaEmerytalna = skladkaEmerytalna.setScale(2, BigDecimal.ROUND_HALF_UP);
        skladkaRentowa = skladkaRentowa.setScale(2, BigDecimal.ROUND_HALF_UP);
        skladkaChorobowa = skladkaChorobowa.setScale(2, BigDecimal.ROUND_HALF_UP);
        skladkaZdrowotna = skladkaZdrowotna.setScale(2, BigDecimal.ROUND_HALF_UP);
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

    private BigDecimal calculateNetSalary(BigDecimal salaryBrutto, BigDecimal skladkaEmerytalna, BigDecimal skladkaRentowa, BigDecimal skladkaChorobowa, BigDecimal skladkaZdrowotna, BigDecimal zaliczkaNaPodatek) {
        return salaryBrutto.subtract(skladkaEmerytalna).subtract(skladkaRentowa).subtract(skladkaChorobowa).subtract(skladkaZdrowotna).subtract(zaliczkaNaPodatek);
    }

    private BigDecimal calculatEmerytalna(BigDecimal salaryBrutto) {
        return salaryBrutto.multiply(ZUSUoP.EMERYTALNA.getRate());
    }

    private BigDecimal calculateRentowa(BigDecimal salaryBrutto) {
        return salaryBrutto.multiply(ZUSUoP.RENTOWA.getRate());
    }

    private BigDecimal calculatChorobowa(BigDecimal salaryBrutto) {
        return salaryBrutto.multiply(ZUSUoP.CHOROBOWA.getRate());
    }

    private BigDecimal calculatZdrowotna(BigDecimal salaryBrutto) {
        BigDecimal baseValue = salaryBrutto.multiply(BigDecimal.valueOf(0.8629));
        return baseValue.multiply(ZUSUoP.ZDROWOTNA.getRate());
    }

    private BigDecimal calculateZaliczkaNaDochodowy(BigDecimal salaryBrutto) {
        BigDecimal yearIncome = salaryBrutto.multiply(NUMBER_OF_MONTHS);
        if (yearIncome.compareTo(KWOTA_WOLNA_OD_PODATKU) <= 0 || salaryBrutto.compareTo(NAJNIZSZA_KRAJOWA) <= 0) {
            return BigDecimal.valueOf(0);
        } else if (yearIncome.compareTo(PROG_PODATKOWY) <= 0 ) {
            BigDecimal baseValue = calculateBaseForTax(salaryBrutto);
            BigDecimal baseValueAfterTax = baseValue.multiply(STAWKA_DOLNY_PROG_PODATKOWY);
            return baseValueAfterTax.subtract(KWOTA_ZMNIEJSZAJACA_PODATEK);
        }else{
            BigDecimal nadwyzka = yearIncome.subtract(PROG_PODATKOWY);
            BigDecimal yearTax = nadwyzka.multiply(STAWKA_GORNY_PROG_PODATKOWY).add(STALA_ROCZNA_DLA_DRUGIEGO_PROGU_PODATKOWEGO);
            return yearTax.divide(NUMBER_OF_MONTHS);

        }
    }


    private BigDecimal calculateBaseForTax(BigDecimal salaryBrutto) {
        BigDecimal zusValue = salaryBrutto.multiply(STALA_ZUS);
        BigDecimal zdrowotna = calculatZdrowotna(salaryBrutto);
        return salaryBrutto.subtract(zusValue).subtract(zdrowotna);

    }

    public BigDecimal calculateEmployerCost(BigDecimal salaryBrutto) {

        return salaryBrutto.multiply(STALA_SUPER_BRUTTO);
    }

}
