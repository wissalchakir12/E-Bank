package org.example.eban2backend.web;


import org.example.eban2backend.DTOS.AccountHistoryDTO;
import org.example.eban2backend.DTOS.AccountOperationDTO;
import org.example.eban2backend.DTOS.BankAccountDTO;
import org.example.eban2backend.Entities.BankAccount;
import org.example.eban2backend.Services.BankAccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankAccountRestAPI
{
    private BankAccountService bankAccountService;

    public BankAccountRestAPI(BankAccountService bankAccountService)
    {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping("accounts/{accountId}")
    public BankAccountDTO getBankAccount(@PathVariable String accountId)
    {
        return bankAccountService.getBankAccount(accountId);
    }

    @GetMapping("/accounts")
    public List<BankAccountDTO> listAccounts()
    {
        return bankAccountService.bankAccountList();
    }

    @GetMapping("/accounts/{accountId}/operations")
    public List<AccountOperationDTO> getHistory(@PathVariable String accountId)
    {
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/accounts/{accountId}/pagOoperations")
    public AccountHistoryDTO getAccountHistory(@PathVariable String accountId,
                                               @RequestParam(name="page",defaultValue ="0") int page ,
                                               @RequestParam(name="size",defaultValue ="5")int size)
    {
        return bankAccountService.getAccountHistory(accountId,page,size);
    }





}
