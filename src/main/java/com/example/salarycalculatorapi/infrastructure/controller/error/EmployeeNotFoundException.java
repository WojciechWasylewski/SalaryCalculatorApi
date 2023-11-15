package com.example.salarycalculatorapi.infrastructure.controller.error;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
