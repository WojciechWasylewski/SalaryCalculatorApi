package com.example.salarycalculatorapi.infrastructure.employeecontroller.error;

import com.example.salarycalculatorapi.infrastructure.employeecontroller.EmployeeRestController;
import com.example.salarycalculatorapi.infrastructure.employeecontroller.error.dto.ErrorEmployeeResponseDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = EmployeeRestController.class)
@Log4j2
public class EmployeeErrorHandler {
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorEmployeeResponseDto> handleException(EmployeeNotFoundException exception){
        log.warn("EmployeeNotFoundException while accesing employee");
        ErrorEmployeeResponseDto errorEmployeeResponseDto = new ErrorEmployeeResponseDto(exception.getMessage(), HttpStatus.NOT_FOUND);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorEmployeeResponseDto);
    }
}
