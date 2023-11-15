package com.example.salarycalculatorapi.infrastructure.controller.dto.response;

import java.math.BigDecimal;

public record EmployeeDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        BigDecimal salaryBrutto
) {
}

