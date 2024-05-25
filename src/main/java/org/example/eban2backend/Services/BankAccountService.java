package org.example.eban2backend.Services;

import org.example.eban2backend.DTOS.*;
import org.example.eban2backend.Entities.BankAccount;
import org.example.eban2backend.Entities.CurrentAccount;
import org.example.eban2backend.Entities.Customer;
import org.example.eban2backend.Entities.SavingAccount;

import java.util.List;

public interface BankAccountService
{
    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId);

    SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId);
    List<CustomerDTO> listCustomers();
    BankAccountDTO getBankAccount(String accountId);
    void debit(String accountId, double amount,String description);
    void credit(String accountId, double amount,String description);
    void transfer(String accountIdSource,String accountIdDestination,double amount);

    List<BankAccountDTO> bankAccountList();


    CustomerDTO getCustomer(Long customerId);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    void deleteCustomer(Long customerId);

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountHistoryDTO getAccountHistory(String accountId, int page, int size);


}
