package com.ariginting.employeeservice.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PayrollRequest {

    private String employeeId;

}
