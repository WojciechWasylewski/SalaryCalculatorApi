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
public class EmployeeUpdater {
    private final EmployeeRepository employeeRepository;
    private final EmployeeRetriever employeeRetriever;

    public void updateById(Long id, Employee newEmployee){
        employeeRetriever.existsById(id);
        employeeRepository.updateById(id, newEmployee);
    }

    public Employee partiallyUpdateById(Long id, Employee employeeFromRequest) {
        Employee employeeFromDataBase = employeeRetriever.findById(id);
        Employee.EmployeeBuilder builder = Employee.builder();
        if(employeeFromRequest.getFirstName() != null){
            builder.firstName(employeeFromRequest.getFirstName());
        }else {
            builder.firstName(employeeFromDataBase.getFirstName());
        }
        if(employeeFromRequest.getLastName() != null){
            builder.lastName(employeeFromRequest.getLastName());
        }else {
            builder.lastName(employeeFromDataBase.getLastName());
        }
        if(employeeFromRequest.getEmail() != null){
            builder.email(employeeFromRequest.getEmail());
        }else {
            builder.email(employeeFromDataBase.getEmail());
        }
        if(employeeFromRequest.getPhoneNumber() != null){
            builder.phoneNumber(employeeFromRequest.getPhoneNumber());
        }else {
            builder.phoneNumber(employeeFromDataBase.getPhoneNumber());
        }
        if(employeeFromRequest.getSalaryBrutto() != null){
            builder.salaryBrutto(employeeFromRequest.getSalaryBrutto());
        }else {
            builder.salaryBrutto(employeeFromDataBase.getSalaryBrutto());
        }
        Employee employeeToSave = builder.build();
        updateById(id, employeeToSave);
        return employeeToSave;

    }
}
