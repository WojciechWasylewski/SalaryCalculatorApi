package com.example.salarycalculatorapi.infrastructure.controller.error.dto;

import org.springframework.http.HttpStatus;

public record ErrorEmployeeResponseDto(String message, HttpStatus status) {
}
