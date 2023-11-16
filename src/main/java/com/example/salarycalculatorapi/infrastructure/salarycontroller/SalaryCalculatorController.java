package com.example.salarycalculatorapi.infrastructure.salarycontroller;

import com.example.salarycalculatorapi.domain.salarycalculator.service.UoPSalaryCalulator;
import com.example.salarycalculatorapi.infrastructure.salarycontroller.dto.SalaryUoPResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

import static com.example.salarycalculatorapi.infrastructure.salarycontroller.mapper.SalaryMapper.mapFromUoPSalary;

@RestController
@AllArgsConstructor
@RequestMapping("kalkulator-wynagrodzen")
public class SalaryCalculatorController {
    private final UoPSalaryCalulator uoPSalaryCalulator;

    @GetMapping("/umowa-o-prace/{kwotaBrutto}")
    public ResponseEntity<SalaryUoPResponseDto> calulateUoP(@PathVariable BigDecimal kwotaBrutto) {
        Map<String, BigDecimal> uopSalary = uoPSalaryCalulator.calculateUoP(kwotaBrutto);
        SalaryUoPResponseDto response = mapFromUoPSalary(uopSalary);
        return ResponseEntity.ok(response);
    }


}
