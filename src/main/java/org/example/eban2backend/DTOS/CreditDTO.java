package org.example.eban2backend.DTOS;


import lombok.Data;

@Data
public class CreditDTO {
    private String accountId;
    private double amount;
    private String decription;
}
