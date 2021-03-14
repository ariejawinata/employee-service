package com.ariginting.employeeservice.service.impl;

import com.ariginting.employeeservice.adaptor.PayrollAdaptor;
import com.ariginting.employeeservice.model.dto.EmployeeDto;
import com.ariginting.employeeservice.model.entity.Employee;
import com.ariginting.employeeservice.model.request.PayrollRequest;
import com.ariginting.employeeservice.model.response.PayrollResponse;
import com.ariginting.employeeservice.repository.EmployeeRepository;
import com.ariginting.employeeservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private PayrollAdaptor payrollAdaptor;

    @Override
    public EmployeeDto find(String id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);

        PayrollRequest payrollRequest = PayrollRequest.builder()
                .employeeId(optionalEmployee.get().getId())
                .build();
        PayrollResponse payrollResponse = payrollAdaptor.getPayroll(payrollRequest);

        return EmployeeDto.builder()
                .name(optionalEmployee.get().getName())
                .email(optionalEmployee.get().getEmail())
                .salary(payrollResponse.getSalary())
                .build();
    }

}
