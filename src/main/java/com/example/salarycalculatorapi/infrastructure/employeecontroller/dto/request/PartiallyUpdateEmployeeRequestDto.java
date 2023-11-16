package com.example.salarycalculatorapi.infrastructure.employeecontroller.dto.request;

import java.math.BigDecimal;

public record PartiallyUpdateEmployeeRequestDto(

        String firstName,
        String lastName,

        String email,

        String phoneNumber,

        BigDecimal salaryBrutto
        ) {
}
