package com.emazon.transactions.infrastructure.output.feign.exceptions;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}