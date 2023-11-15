package com.example.salarycalculatorapi.domain.service;

import com.example.salarycalculatorapi.domain.model.Employee;
import com.example.salarycalculatorapi.domain.repository.EmployeeRepository;
import com.example.salarycalculatorapi.infrastructure.controller.error.EmployeeNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class EmployeeRetriever {
    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        log.info("retrieving all employees");
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with id: " + id + " does not exist"));

    }

    public void existsById(Long id) {
        if (!employeeRepository.existsById(id)){
            throw new EmployeeNotFoundException("Employee with id: " + id + " does not exist");
        }
    }
}
