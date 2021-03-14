package com.ariginting.employeeservice.controller;

import com.ariginting.employeeservice.model.dto.EmployeeDto;
import com.ariginting.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/{id}")
    EmployeeDto find(@PathVariable String id) {
        return employeeService.find(id);
    }

}
