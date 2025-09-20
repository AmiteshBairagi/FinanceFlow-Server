package com.amitesh.finance_flow.customException.budget;

import com.amitesh.finance_flow.customException.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BudgetNotFoundException extends ApplicationException {
    public BudgetNotFoundException(String message){
        super(message);
    }
}
