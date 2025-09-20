package com.amitesh.finance_flow.customException.auth;

import com.amitesh.finance_flow.customException.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenActionException extends ApplicationException {
    public ForbiddenActionException(String message){
        super(message);
    }
}
