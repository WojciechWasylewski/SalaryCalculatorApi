package com.example.salarycalculatorapi.domain.apivalidation;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ApiValidationErrorResponseDto(List<String> errors, HttpStatus status) {
}
