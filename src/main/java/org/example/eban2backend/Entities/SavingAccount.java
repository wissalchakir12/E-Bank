package org.example.eban2backend.Entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@DiscriminatorValue("SA")

@NoArgsConstructor
@AllArgsConstructor
public class SavingAccount extends BankAccount
{
    private double interestRate;
}
