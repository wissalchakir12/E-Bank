package org.example.eban2backend.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.eban2backend.Enums.OperationType;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountOperation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private  Long id;
    private Date operationDate;
    private double amount ;
    @Enumerated(EnumType.STRING)
    private OperationType type ;
    @ManyToOne
    private BankAccount bankAccount;
    private String description;
}
