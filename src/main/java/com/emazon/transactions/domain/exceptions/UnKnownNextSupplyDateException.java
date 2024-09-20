package com.emazon.transactions.domain.exceptions;

public class UnKnownNextSupplyDateException extends RuntimeException {
    
    public UnKnownNextSupplyDateException(String message) {
        super(message);
    }
}
