package org.example.eban2backend.Exeption;

public class BankAccontNotFoundExeption extends RuntimeException{
    public BankAccontNotFoundExeption(String message) {
        super(message);
    }
}
