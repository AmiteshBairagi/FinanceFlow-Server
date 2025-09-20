package com.amitesh.finance_flow.customException.user;

import com.amitesh.finance_flow.customException.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException(String message){
        super(message);
    }
}
