package org.example.eban2backend.Mappers;

import org.example.eban2backend.DTOS.AccountOperationDTO;
import org.example.eban2backend.DTOS.CurrentBankAccountDTO;
import org.example.eban2backend.DTOS.CustomerDTO;
import org.example.eban2backend.DTOS.SavingBankAccountDTO;
import org.example.eban2backend.Entities.AccountOperation;
import org.example.eban2backend.Entities.CurrentAccount;
import org.example.eban2backend.Entities.Customer;
import org.example.eban2backend.Entities.SavingAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BankAccountMapperImp
{

    public CustomerDTO fromCustomer(Customer customer)
    {
        CustomerDTO customerDTO=new CustomerDTO();
        BeanUtils.copyProperties(customer,customerDTO);// au lieu d'utiliser setters on copie les propriétés

        return customerDTO;

    }
    public Customer fromCustomerDTO(CustomerDTO customerDTO)
    {
        Customer customer=new Customer();
        BeanUtils.copyProperties(customerDTO,customer);

        return customer;
    }




    public SavingAccount fromSavingBankAccountDTO(SavingBankAccountDTO savingBankAccountDTO)
    {
        SavingAccount savingAccount=new SavingAccount();
        BeanUtils.copyProperties(savingBankAccountDTO,savingAccount);
        savingAccount.setCustomer(fromCustomerDTO(savingBankAccountDTO.getCustomerDTO()));
        return savingAccount;
    }



    public SavingBankAccountDTO fromSavingBankAccount(SavingAccount savingAccount)
    {
        SavingBankAccountDTO savingBankAccountDTO=new SavingBankAccountDTO();
        BeanUtils.copyProperties(savingAccount,savingBankAccountDTO);
        //On récupère le customer de saving account et on le rend customerDTO pour l'affecter au customerDTO de savingBankAccountDTO.
        savingBankAccountDTO.setCustomerDTO(fromCustomer(savingAccount.getCustomer()));
        savingBankAccountDTO.setType(savingAccount.getClass().getSimpleName());
        return savingBankAccountDTO;
    }

    public CurrentBankAccountDTO fromCurrentBankAccount(CurrentAccount currentAccount)
    {
        CurrentBankAccountDTO currentBankAccountDTO=new CurrentBankAccountDTO();
        BeanUtils.copyProperties(currentAccount,currentBankAccountDTO);
        currentBankAccountDTO.setCustomerDTO(fromCustomer(currentAccount.getCustomer()));
        currentBankAccountDTO.setType(currentAccount.getClass().getSimpleName());
        return currentBankAccountDTO;
    }
    public CurrentAccount fromCurrentBankAccountDTO(CurrentBankAccountDTO currentBankAccountDTO)
    {
        CurrentAccount currentAccount=new CurrentAccount();
        BeanUtils.copyProperties(currentBankAccountDTO,currentAccount);
        currentAccount.setCustomer(fromCustomerDTO(currentBankAccountDTO.getCustomerDTO()));
        return currentAccount;
    }


    public AccountOperationDTO fromAccountOperation(AccountOperation accountOperation){
        AccountOperationDTO accountOperationDTO=new AccountOperationDTO();
        BeanUtils.copyProperties(accountOperation,accountOperationDTO);
        return accountOperationDTO;
    }


}
