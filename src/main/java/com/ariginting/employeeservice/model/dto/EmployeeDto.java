package com.ariginting.employeeservice.model.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class EmployeeDto {

    private String name;
    private String email;
    private BigDecimal salary;

}
