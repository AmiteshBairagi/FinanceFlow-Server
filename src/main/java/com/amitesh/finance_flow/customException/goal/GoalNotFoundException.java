package com.amitesh.finance_flow.customException.goal;

import com.amitesh.finance_flow.customException.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class GoalNotFoundException extends ApplicationException {
    public GoalNotFoundException(String message){
        super(message);
    }
}
