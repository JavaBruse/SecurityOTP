package com.javabruse.domain.dto;

import lombok.Data;

@Data
public class OtpValidationRequest {
    private String operationId;
    private String code;
}
