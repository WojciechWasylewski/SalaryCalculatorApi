package com.example.salarycalculatorapi.infrastructure.controller.dto.response;

import org.springframework.http.HttpStatus;

public record DeleteEmployeeResponseDto(String message, HttpStatus status) {
}
