package com.example.salarycalculatorapi.infrastructure.controller.dto.response;

import java.util.List;

public record GetAllEmployeeResponseDto(List<EmployeeDto> employees) {
}
