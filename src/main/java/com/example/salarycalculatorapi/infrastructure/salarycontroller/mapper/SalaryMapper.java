package com.example.salarycalculatorapi.infrastructure.salarycontroller.mapper;

import com.example.salarycalculatorapi.infrastructure.salarycontroller.dto.SalaryUoPResponseDto;

import java.math.BigDecimal;
import java.util.Map;

public class SalaryMapper {
    public static SalaryUoPResponseDto mapFromUoPSalary(Map<String, BigDecimal> uopSalary) {
        return new SalaryUoPResponseDto(
                uopSalary.get("Składka Emerytalna"),
                uopSalary.get("Składka Rentowa"),
                uopSalary.get("Składka Chorobowa"),
                uopSalary.get("Składka Zdrowotna"),
                uopSalary.get("Zaliczka na podatek"),
                uopSalary.get("Koszt pracownika"),
                uopSalary.get("Wynagrodzenie netto")
        );
    }
}
