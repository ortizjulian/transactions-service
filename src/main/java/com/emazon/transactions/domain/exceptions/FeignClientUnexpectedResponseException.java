package com.emazon.transactions.domain.exceptions;

public class FeignClientUnexpectedResponseException extends RuntimeException {
    public FeignClientUnexpectedResponseException(String message) {
        super(message);
    }
}
