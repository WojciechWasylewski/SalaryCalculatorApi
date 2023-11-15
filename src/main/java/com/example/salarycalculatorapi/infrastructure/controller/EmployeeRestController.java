package com.example.salarycalculatorapi.infrastructure.controller;

import com.example.salarycalculatorapi.domain.model.Employee;
import com.example.salarycalculatorapi.domain.service.EmployeeAdder;
import com.example.salarycalculatorapi.domain.service.EmployeeDeleter;
import com.example.salarycalculatorapi.domain.service.EmployeeRetriever;
import com.example.salarycalculatorapi.domain.service.EmployeeUpdater;
import com.example.salarycalculatorapi.infrastructure.controller.dto.request.PartiallyUpdateEmployeeRequestDto;
import com.example.salarycalculatorapi.infrastructure.controller.dto.request.PostEmployeeRequestDto;
import com.example.salarycalculatorapi.infrastructure.controller.dto.request.UpdateEmployeeRequestDto;
import com.example.salarycalculatorapi.infrastructure.controller.dto.response.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.salarycalculatorapi.infrastructure.controller.mapper.EmployeeMapper.*;

@RestController
@AllArgsConstructor
@RequestMapping("/employees")
public class EmployeeRestController {
    private final EmployeeRetriever employeeRetriever;
    private final EmployeeAdder employeeAdder;
    private final EmployeeDeleter employeeDeleter;
    private final EmployeeUpdater employeeUpdater;

    @GetMapping()
    public ResponseEntity<GetAllEmployeeResponseDto> getAllEmployees() {
        List<Employee> allEmployees = employeeRetriever.findAll();
        GetAllEmployeeResponseDto response = mapFromEmployeeToGetAllEmployeesResponseDto(allEmployees);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetEmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
        Employee employeeById = employeeRetriever.findById(id);
        GetEmployeeResponseDto responseById = mapFromEmployeeToGetEmployeeResponseDto(employeeById);
        return ResponseEntity.ok(responseById);
    }

    @PostMapping()
    public ResponseEntity<PostEmployeeResponseDto> postEmployee(@RequestBody @Valid PostEmployeeRequestDto request) {
        Employee employee = mapFromPostEmployeeRequestDtoToEmployee(request);
        Employee savedEmployee = employeeAdder.addEmployee(employee);
        PostEmployeeResponseDto response = mapFromEmployeeToPostEmployeeResponseDto(savedEmployee);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteEmployeeResponseDto> deleteEmployeeById(@PathVariable Long id) {
        employeeDeleter.deleteById(id);
        DeleteEmployeeResponseDto response = mapFromEmployeeToDeleteEmployeeResponseDto(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateEmployeeResponseDto> updateEmployee(@PathVariable Long id,
                                                                    @RequestBody @Valid UpdateEmployeeRequestDto request) {
        Employee newEmployee = mapFromUpdateEmployeeResponseDtoToEmployee(request);
        employeeUpdater.updateById(id, newEmployee);
        UpdateEmployeeResponseDto response = mapFromEmployeeToUpdateEmployeeResponseDto(newEmployee);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<PartiallyUpdateEmployeeResponseDto> partiallyUpdateEmployee(@PathVariable Long id,
                                                                                      @RequestBody PartiallyUpdateEmployeeRequestDto patchRequest) {
        Employee updatedEmployee = mapPartiallyUpdateEmployeeRequestDtoToEmployee(patchRequest);
        Employee savedEmployee = employeeUpdater.partiallyUpdateById(id, updatedEmployee);
        PartiallyUpdateEmployeeResponseDto response = mapFromEmployeeToPartiallyUpdateEmployeeResponseDto(savedEmployee);
        return ResponseEntity.ok(response);
    }

}
