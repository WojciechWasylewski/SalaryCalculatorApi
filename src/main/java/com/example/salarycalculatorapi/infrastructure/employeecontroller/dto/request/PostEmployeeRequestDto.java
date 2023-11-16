package com.example.salarycalculatorapi.infrastructure.employeecontroller.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PostEmployeeRequestDto(
        @NotNull(message = "firstName must be not null")
        @NotEmpty(message = "firstName must be not empty")
        String firstName,
        @NotNull(message = "lastName must be not null")
        @NotEmpty(message = "lastName must be not empty")
        String lastName,
        @NotNull(message = "email must be not null")
        @NotEmpty(message = "email must be not empty")
        String email,
        @NotNull(message = "phoneNumber must be not null")
        @NotEmpty(message = "phoneNumber must be not empty")
        String phoneNumber,
        @NotNull(message = "salaryBrutto must be not null")
        @DecimalMin(value = "0.01", inclusive = false, message = "salaryBrutto must be greater than 0")
        BigDecimal salaryBrutto

) {
}
