package com.amitesh.finance_flow.customException.budget;

import com.amitesh.finance_flow.customException.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BudgetExceededException extends ApplicationException {
    public BudgetExceededException(String message){
        super(message);
    }
}
