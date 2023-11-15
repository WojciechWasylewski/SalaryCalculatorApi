package com.example.salarycalculatorapi.domain.repository;

import com.example.salarycalculatorapi.domain.model.Employee;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends Repository<Employee, Long> {

    List<Employee> findAll();

    Optional<Employee> findById(Long id);

    Employee save(Employee employee);

    boolean existsById(Long id);
    @Modifying
    void deleteById(Long id);
    @Modifying
    @Query("UPDATE Employee e SET e.firstName = :#{#newEmployee.firstName}, e.lastName = :#{#newEmployee.lastName}, e.email = :#{#newEmployee.email}, e.phoneNumber = :#{#newEmployee.phoneNumber}, e.salaryBrutto = :#{#newEmployee.salaryBrutto} WHERE e.id = :id")
    void updateById(Long id, Employee newEmployee);
}
