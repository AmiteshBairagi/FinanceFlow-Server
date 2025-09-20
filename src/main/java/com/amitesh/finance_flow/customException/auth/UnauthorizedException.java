package com.amitesh.finance_flow.customException.auth;

import com.amitesh.finance_flow.customException.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends ApplicationException {
    public UnauthorizedException(String message){
        super(message);
    }
}
