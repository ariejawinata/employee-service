package com.ariginting.employeeservice.adaptor;

import com.ariginting.employeeservice.model.request.PayrollRequest;
import com.ariginting.employeeservice.model.response.PayrollResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PayrollAdaptor {

    @Value("${integration.payroll.payroll.url}")
    private String payrollUrl;

    private RestTemplate restTemplate;

    public PayrollAdaptor(@Qualifier(value = "restTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PayrollResponse getPayroll(PayrollRequest request) {
        return restTemplate.exchange(
                payrollUrl+"/"+request.getEmployeeId(),
                HttpMethod.GET,
                new HttpEntity<>(request),
                PayrollResponse.class).getBody();
    }

}
