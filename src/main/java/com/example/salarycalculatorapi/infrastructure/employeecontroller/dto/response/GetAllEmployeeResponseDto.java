package com.example.salarycalculatorapi.infrastructure.employeecontroller.dto.response;

import java.util.List;

public record GetAllEmployeeResponseDto(List<EmployeeDto> employees) {
}
