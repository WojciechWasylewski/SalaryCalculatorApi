package com.example.salarycalculatorapi.infrastructure.employeecontroller.dto.response;

import java.math.BigDecimal;

public record UpdateEmployeeResponseDto(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        BigDecimal salaryBrutto ) {
}
