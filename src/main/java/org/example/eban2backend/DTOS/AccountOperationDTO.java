package org.example.eban2backend.DTOS;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eban2backend.Entities.BankAccount;
import org.example.eban2backend.Enums.OperationType;

import java.util.Date;


@Data

public class AccountOperationDTO
{


    private  Long id;
    private Date operationDate;
    private double amount ;

    private OperationType type ;
    private String description;
}
