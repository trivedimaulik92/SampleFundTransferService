package com.example.samplefundtransferservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ActiveFundTransferLimitExceededException extends RuntimeException {

    private long customerId;

    public ActiveFundTransferLimitExceededException(Long customerId){
        super(String.format("Customer %s already has max allowed active fund transfers.", customerId));
        this.customerId = customerId;
    }
}
