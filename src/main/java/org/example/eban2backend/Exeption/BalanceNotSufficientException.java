package org.example.eban2backend.Exeption;

public class BalanceNotSufficientException extends RuntimeException {
    public BalanceNotSufficientException(String message) {
        super(message);
    }
}
