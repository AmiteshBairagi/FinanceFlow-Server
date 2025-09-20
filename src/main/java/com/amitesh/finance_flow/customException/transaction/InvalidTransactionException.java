package com.amitesh.finance_flow.customException.transaction;

import com.amitesh.finance_flow.customException.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTransactionException extends ApplicationException {
    public InvalidTransactionException(String message){
        super(message);
    }
}
