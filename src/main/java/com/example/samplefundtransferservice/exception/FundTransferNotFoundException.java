package com.example.samplefundtransferservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FundTransferNotFoundException extends RuntimeException {

    private String fieldName;
    private Long fieldValue;

    public FundTransferNotFoundException(String fieldName, Long fieldValue){
        super(String.format("Fund Transfer record not found with %s : '%s'", fieldName, fieldValue));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
