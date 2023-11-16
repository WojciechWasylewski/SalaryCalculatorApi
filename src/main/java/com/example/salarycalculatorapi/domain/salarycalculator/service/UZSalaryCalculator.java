package com.example.salarycalculatorapi.domain.salarycalculator.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class UZSalaryCalculator {
    private static final BigDecimal STAWKA_OPROCENTOWANIA_PODATKU = BigDecimal.valueOf(0.12);
    private static final BigDecimal STALA_SUPER_BRUTTO = BigDecimal.valueOf(1.2);
    private static final BigDecimal KUP = BigDecimal.valueOf(0.2); // KUP - Koszty uzyskania przychodu dla uz to 20% podstawy


    public Map<String, BigDecimal> calculateUZ(BigDecimal salaryBrutto) {
        BigDecimal skladkaEmerytalna = calculatEmerytalna(salaryBrutto);
        BigDecimal skladkaRentowa = calculateRentowa(salaryBrutto);
        BigDecimal skladkaChorobowa = BigDecimal.valueOf(0);
        BigDecimal skladkaZdrowotna = calculatZdrowotna(salaryBrutto);
        BigDecimal zaliczkaNaPodatek = calculateZaliczkaNaDochodowy(salaryBrutto);
        BigDecimal superBrutto = calculateEmployerCost(salaryBrutto);
        BigDecimal salaryNetto = calculateNetSalary(salaryBrutto, skladkaEmerytalna, skladkaRentowa, skladkaChorobowa, skladkaZdrowotna, zaliczkaNaPodatek);

        skladkaEmerytalna = skladkaEmerytalna.setScale(2, BigDecimal.ROUND_HALF_UP);
        skladkaRentowa = skladkaRentowa.setScale(2, BigDecimal.ROUND_HALF_UP);
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
        return salaryBrutto.multiply(ZUSuz.EMERYTALNA.getRate());
    }

    private BigDecimal calculateRentowa(BigDecimal salaryBrutto) {
        return salaryBrutto.multiply(ZUSuz.RENTOWA.getRate());
    }

    private BigDecimal calculatZdrowotna(BigDecimal salaryBrutto) {
        BigDecimal baseValue = salaryBrutto.multiply(BigDecimal.valueOf(0.8874));
        return baseValue.multiply(ZUSuz.ZDROWOTNA.getRate());
    }

    private BigDecimal calculateZaliczkaNaDochodowy(BigDecimal salaryBrutto) {
        BigDecimal kosztUzyskaniaPrzychodu = calculateKosztyPrzychodu(salaryBrutto);
        BigDecimal base = salaryBrutto.subtract(kosztUzyskaniaPrzychodu);
        BigDecimal zusTaxes = calculateAllzusTaxes(salaryBrutto);
        BigDecimal baseAfterZus = base.subtract(zusTaxes);
        return baseAfterZus.multiply(STAWKA_OPROCENTOWANIA_PODATKU);
    }

    private BigDecimal calculateAllzusTaxes(BigDecimal salaryBrutto) {
        return salaryBrutto.multiply(BigDecimal.valueOf(0.1126));
    }

    public BigDecimal calculateKosztyPrzychodu(BigDecimal salaryBrutto) {
        BigDecimal base = salaryBrutto.multiply(BigDecimal.valueOf(0.8874));
        return base.multiply(KUP);
    }


    public BigDecimal calculateEmployerCost(BigDecimal salaryBrutto) {

        return salaryBrutto.multiply(STALA_SUPER_BRUTTO);
    }
}
