package com.example.salarycalculatorapi.domain.service;

import com.example.salarycalculatorapi.domain.model.Employee;
import com.example.salarycalculatorapi.domain.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
@AllArgsConstructor
public class EmployeeAdder {
    private final EmployeeRepository employeeRepository;

    public Employee addEmployee(Employee employee){
        log.info("adding new employee: " + employee);
        return employeeRepository.save(employee);
    }
}
