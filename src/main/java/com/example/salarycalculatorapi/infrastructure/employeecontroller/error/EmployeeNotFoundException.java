package com.example.salarycalculatorapi.infrastructure.employeecontroller.error;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
