package com.amitesh.finance_flow.customException.auth;

import com.amitesh.finance_flow.customException.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TokenExpiredException extends ApplicationException {
    public TokenExpiredException(String message){
        super(message);
    }
}
