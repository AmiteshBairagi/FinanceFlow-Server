package com.amitesh.finance_flow.customException;

public class ApplicationException extends RuntimeException {

    public ApplicationException (String message){
        super(message);
    }

    public ApplicationException (String message, Throwable cause ){
        super(message, cause);
    }
}
