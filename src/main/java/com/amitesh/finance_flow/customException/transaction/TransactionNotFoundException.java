package com.amitesh.finance_flow.customException.transaction;

import com.amitesh.finance_flow.customException.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends ApplicationException {
    public TransactionNotFoundException(String message){
        super(message);
    }
}
