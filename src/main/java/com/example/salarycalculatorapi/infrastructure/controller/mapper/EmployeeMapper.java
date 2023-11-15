package com.example.salarycalculatorapi.infrastructure.controller.mapper;

import com.example.salarycalculatorapi.domain.model.Employee;
import com.example.salarycalculatorapi.infrastructure.controller.dto.request.PartiallyUpdateEmployeeRequestDto;
import com.example.salarycalculatorapi.infrastructure.controller.dto.response.PartiallyUpdateEmployeeResponseDto;
import com.example.salarycalculatorapi.infrastructure.controller.dto.request.UpdateEmployeeRequestDto;
import com.example.salarycalculatorapi.infrastructure.controller.dto.response.UpdateEmployeeResponseDto;
import com.example.salarycalculatorapi.infrastructure.controller.dto.response.DeleteEmployeeResponseDto;
import com.example.salarycalculatorapi.infrastructure.controller.dto.response.GetEmployeeResponseDto;
import com.example.salarycalculatorapi.infrastructure.controller.dto.request.PostEmployeeRequestDto;
import com.example.salarycalculatorapi.infrastructure.controller.dto.response.PostEmployeeResponseDto;
import com.example.salarycalculatorapi.infrastructure.controller.dto.response.EmployeeDto;
import com.example.salarycalculatorapi.infrastructure.controller.dto.response.GetAllEmployeeResponseDto;
import org.springframework.http.HttpStatus;

import java.util.List;

public class EmployeeMapper {
    private static EmployeeDto mapFromEmployeeToEmployeeDto(Employee employee) {
        return new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhoneNumber(), employee.getSalaryBrutto());
    }

    public static GetAllEmployeeResponseDto mapFromEmployeeToGetAllEmployeesResponseDto(List<Employee> employees) {
        List<EmployeeDto> employeeDtos = employees.stream()
                .map(EmployeeMapper::mapFromEmployeeToEmployeeDto).toList();
        return new GetAllEmployeeResponseDto(employeeDtos);
    }

    public static GetEmployeeResponseDto mapFromEmployeeToGetEmployeeResponseDto(Employee employee) {
        EmployeeDto employeeDto = mapFromEmployeeToEmployeeDto(employee);
        return new GetEmployeeResponseDto(employeeDto);
    }

    public static Employee mapFromPostEmployeeRequestDtoToEmployee(PostEmployeeRequestDto request) {
        return new Employee(request.firstName(), request.lastName(), request.email(), request.phoneNumber(), request.salaryBrutto());
    }

    public static PostEmployeeResponseDto mapFromEmployeeToPostEmployeeResponseDto(Employee employee) {
        EmployeeDto employeeDto = mapFromEmployeeToEmployeeDto(employee);
        return new PostEmployeeResponseDto(employeeDto);
    }

    public static DeleteEmployeeResponseDto mapFromEmployeeToDeleteEmployeeResponseDto(Long id){
        return new DeleteEmployeeResponseDto("You deleted employee with id: " + id, HttpStatus.OK);
    }

    public static Employee mapFromUpdateEmployeeResponseDtoToEmployee(UpdateEmployeeRequestDto dto){
        return new Employee(dto.firstName(), dto.lastName(), dto.email(), dto.phoneNumber(), dto.salaryBrutto());
    }

    public static UpdateEmployeeResponseDto mapFromEmployeeToUpdateEmployeeResponseDto(Employee employee){
        return new UpdateEmployeeResponseDto(employee.getFirstName(), employee.getLastName(), employee.getEmail(), employee.getPhoneNumber(), employee.getSalaryBrutto());
    }

    public static Employee mapPartiallyUpdateEmployeeRequestDtoToEmployee(PartiallyUpdateEmployeeRequestDto dto){
        return new Employee(dto.firstName(), dto.lastName(), dto.email(), dto.phoneNumber(), dto.salaryBrutto());
    }

    public static PartiallyUpdateEmployeeResponseDto mapFromEmployeeToPartiallyUpdateEmployeeResponseDto (Employee employee){
        EmployeeDto employeeDto = mapFromEmployeeToEmployeeDto(employee);
        return new PartiallyUpdateEmployeeResponseDto(employeeDto);
    }


}
