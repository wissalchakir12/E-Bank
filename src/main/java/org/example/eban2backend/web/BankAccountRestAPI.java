package org.example.eban2backend.web;


import org.example.eban2backend.DTOS.*;
import org.example.eban2backend.Entities.BankAccount;
import org.example.eban2backend.Services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*/*")
public class BankAccountRestAPI
{
    private BankAccountService bankAccountService;

    public BankAccountRestAPI(BankAccountService bankAccountService)
    {
        this.bankAccountService = bankAccountService;
    }

    @CrossOrigin("*")
    @GetMapping("accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId)
    {
        return bankAccountService.getBankAccount(accountId);
    }

    @CrossOrigin("*")
    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts()
    {
        return bankAccountService.bankAccountList();
    }

    @CrossOrigin("*")
    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId)
    {
        return bankAccountService.accountHistory(accountId);
    }

    @CrossOrigin("*")
    @GetMapping("/accounts/{accountId}/pagOoperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
                                               @RequestParam(name="page",defaultValue ="0") int page ,
                                               @RequestParam(name="size",defaultValue ="5")int size)
    {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }


    @CrossOrigin("*")
    @PostMapping("/accounts/debit")
    public DebitDTO debit(@RequestBody DebitDTO debitDTO)  {
        this.bankAccountService.debit(debitDTO.getAccountId(),debitDTO.getAmount(),debitDTO.getDescription());
        return debitDTO;
    }
    @CrossOrigin("*")
    @PostMapping("/accounts/credit")
    public CreditDTO credit(@RequestBody CreditDTO creditDTO)  {
        this.bankAccountService.credit(creditDTO.getAccountId(),creditDTO.getAmount(),creditDTO.getDecription());
        return creditDTO;
    }



}







