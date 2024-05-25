package org.example.eban2backend.DTOS;

import lombok.Data;
import org.example.eban2backend.Enums.AccountStatus;

import java.util.Date;


@Data

public class CurrentBankAccountDTO extends BankAccountDTO
{
    private String id;
    private double  balance;
    private Date createdAt;
    private AccountStatus status;
    private CustomerDTO customerDTO;
    private double interestRate;


}
