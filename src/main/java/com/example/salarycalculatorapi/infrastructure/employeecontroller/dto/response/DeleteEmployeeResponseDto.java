package com.example.salarycalculatorapi.infrastructure.employeecontroller.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteEmployeeResponseDto(String message, HttpStatus status) {
}
