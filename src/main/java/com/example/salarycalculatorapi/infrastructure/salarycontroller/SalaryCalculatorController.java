package com.example.salarycalculatorapi.infrastructure.salarycontroller;

import com.example.salarycalculatorapi.domain.salarycalculator.service.UZSalaryCalculator;
import com.example.salarycalculatorapi.domain.salarycalculator.service.UoDSalaryCalculator;
import com.example.salarycalculatorapi.domain.salarycalculator.service.UoPSalaryCalculator;
import com.example.salarycalculatorapi.infrastructure.salarycontroller.dto.SalaryResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

import static com.example.salarycalculatorapi.infrastructure.salarycontroller.mapper.SalaryMapper.mapFromSalaryToSalaryResponseDto;

@RestController
@AllArgsConstructor
@RequestMapping("kalkulator-wynagrodzen")
public class SalaryCalculatorController {
    private final UoPSalaryCalculator uoPSalaryCalculator;
    private final UZSalaryCalculator uzSalaryCalculator;
    private final UoDSalaryCalculator uoDSalaryCalculator;


    @GetMapping("/umowa-o-prace/{kwotaBrutto}")
    public ResponseEntity<SalaryResponseDto> calulateUoP(@PathVariable BigDecimal kwotaBrutto) {
        Map<String, BigDecimal> uopSalary = uoPSalaryCalculator.calculateUoP(kwotaBrutto);
        SalaryResponseDto response = mapFromSalaryToSalaryResponseDto(uopSalary);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/umowa-zlecenie/{kwotaBrutto}")
    public ResponseEntity<SalaryResponseDto> calulateUZ(@PathVariable BigDecimal kwotaBrutto) {
        Map<String, BigDecimal> uzSalary = uzSalaryCalculator.calculateUZ(kwotaBrutto);
        SalaryResponseDto response = mapFromSalaryToSalaryResponseDto(uzSalary);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/umowa-o-dzieło/{kwotaBrutto}")
    public ResponseEntity<SalaryResponseDto> calulateUoD(@PathVariable BigDecimal kwotaBrutto) {
        Map<String, BigDecimal> uodSalary = uoDSalaryCalculator.calculateUoD(kwotaBrutto);
        SalaryResponseDto response = mapFromSalaryToSalaryResponseDto(uodSalary);
        return ResponseEntity.ok(response);
    }


}
