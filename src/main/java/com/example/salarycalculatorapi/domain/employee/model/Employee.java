package com.example.salarycalculatorapi.domain.employee.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname", nullable = false)
    private String firstName;

    @Column(name = "lastname", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(name = "phonenumber", nullable = false)
    private String phoneNumber;

    @Column(name = "salarybrutto", nullable = false)
    private BigDecimal salaryBrutto;


    public Employee(String firstName, String lastName, String email, String phoneNumber, BigDecimal salaryBrutto) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salaryBrutto = salaryBrutto;
    }
}
