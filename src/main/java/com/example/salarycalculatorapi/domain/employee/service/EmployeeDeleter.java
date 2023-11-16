package com.example.salarycalculatorapi.domain.employee.service;

import com.example.salarycalculatorapi.domain.employee.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Transactional
@AllArgsConstructor
public class EmployeeDeleter {
    private final EmployeeRepository employeeRepository;
    private final EmployeeRetriever employeeRetriever;

    public void deleteById(Long id){
        employeeRetriever.existsById(id);
        log.info("deleting employee with id: " + id);
        employeeRepository.deleteById(id);
    }
}
