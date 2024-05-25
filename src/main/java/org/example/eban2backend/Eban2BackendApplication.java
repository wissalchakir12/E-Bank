package org.example.eban2backend;

import org.example.eban2backend.DTOS.BankAccountDTO;
import org.example.eban2backend.DTOS.CurrentBankAccountDTO;
import org.example.eban2backend.DTOS.CustomerDTO;
import org.example.eban2backend.DTOS.SavingBankAccountDTO;
import org.example.eban2backend.Entities.AccountOperation;
import org.example.eban2backend.Entities.CurrentAccount;
import org.example.eban2backend.Entities.Customer;
import org.example.eban2backend.Entities.SavingAccount;
import org.example.eban2backend.Enums.AccountStatus;
import org.example.eban2backend.Enums.OperationType;
import org.example.eban2backend.Exeption.CustomerNotFoundException;
import org.example.eban2backend.Repositories.AccountOperationRepository;
import org.example.eban2backend.Repositories.BankAccountRepository;
import org.example.eban2backend.Repositories.CustomerRepository;
import org.example.eban2backend.Services.BankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class  Eban2BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(Eban2BackendApplication.class, args);
    }


}

