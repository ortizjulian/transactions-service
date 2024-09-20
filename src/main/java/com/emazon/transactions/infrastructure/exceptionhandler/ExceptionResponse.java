package com.emazon.transactions.infrastructure.exceptionhandler;


import com.emazon.transactions.utils.Constants;

public enum ExceptionResponse {
    SERVICE_UNAVAILABLE(Constants.EXCEPTION_SERVICE_UNAVAILABLE);
    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}