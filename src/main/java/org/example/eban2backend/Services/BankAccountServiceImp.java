package org.example.eban2backend.Services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.eban2backend.DTOS.*;
import org.example.eban2backend.Entities.*;
import org.example.eban2backend.Enums.OperationType;
import org.example.eban2backend.Exeption.BalanceNotSufficientException;
import org.example.eban2backend.Exeption.BankAccontNotFoundExeption;
import org.example.eban2backend.Exeption.CustomerNotFoundException;
import org.example.eban2backend.Mappers.BankAccountMapperImp;
import org.example.eban2backend.Repositories.AccountOperationRepository;
import org.example.eban2backend.Repositories.BankAccountRepository;
import org.example.eban2backend.Repositories.CustomerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j //Pour les messages
public class BankAccountServiceImp implements BankAccountService
{


    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;
    private AccountOperationRepository accountOperationRepository;
    private BankAccountMapperImp dtoMapper;



    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
       log.info("Saving new Customer");
       Customer customer=dtoMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer=customerRepository.save(customer);
       return dtoMapper.fromCustomer(savedCustomer);
    }

    @Override
    public CurrentBankAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) {
        CurrentAccount currentAccount=new CurrentAccount();
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if(customer==null)
        {
            throw new CustomerNotFoundException("Customer not found!");
        }
        currentAccount.setId(UUID.randomUUID().toString() );
        currentAccount.setCreatedAt(new Date());
        currentAccount.setBalance(initialBalance);
        currentAccount.setCustomer(customer);
        currentAccount.setOverDraft(overDraft);
        CurrentAccount SavedBankAccount= bankAccountRepository.save(currentAccount);
        return dtoMapper.fromCurrentBankAccount(SavedBankAccount);
    }

    @Override
    public SavingBankAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) {
        SavingAccount savingAccount=new SavingAccount();
        Customer customer=customerRepository.findById(customerId).orElse(null);
        if(customer==null)
        {
            throw new CustomerNotFoundException("Customer not found!");
        }
        savingAccount.setId(UUID.randomUUID().toString() );
        savingAccount.setCreatedAt(new Date());
        savingAccount.setBalance(initialBalance);
        savingAccount.setCustomer(customer);
        savingAccount.setInterestRate(interestRate);
        SavingAccount SavedBankAccount= bankAccountRepository.save(savingAccount);
        return dtoMapper.fromSavingBankAccount(SavedBankAccount);
    }



    @Override
    public List<CustomerDTO> listCustomers()
    {

        List<Customer > customers=customerRepository.findAll();
        //customers.stream().map(customer->dtoMapper.fromCustomer(customer)).collect(Collectors.toList());
        List<CustomerDTO> customerDTOS=new ArrayList<>();
        for(Customer customer:customers)
        {
            CustomerDTO customerDTO=dtoMapper.fromCustomer(customer);
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;


    }

    @Override
    public BankAccountDTO getBankAccount(String accountId)
    {
        BankAccount bankAccount=bankAccountRepository.findById(accountId).orElseThrow(()->new BankAccontNotFoundExeption("Account Not Found"));
        if(bankAccount instanceof SavingAccount)
        {
            SavingAccount savingAccount = (SavingAccount) bankAccount;
            return dtoMapper.fromSavingBankAccount(savingAccount);
        }
        else
        {
            CurrentAccount curentAccount = (CurrentAccount) bankAccount;
            return dtoMapper.fromCurrentBankAccount(curentAccount);
        }
    }

    @Override
    public void debit(String accountId, double amount, String description)
    {
        BankAccount bankAccount=bankAccountRepository.findById(accountId).orElseThrow(()->new BankAccontNotFoundExeption("Account Not Found"));
        if(bankAccount.getBalance()<amount) throw new BalanceNotSufficientException("Balance not sufficient");
        AccountOperation accountOperation=new AccountOperation();
        accountOperation.setType(OperationType.DEBIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);

    }


    @Override
    public void credit(String accountId, double amount, String description) {
        BankAccount bankAccount=bankAccountRepository.findById(accountId).orElseThrow(()->new BankAccontNotFoundExeption("Account Not Found"));

        AccountOperation accountOperation=new AccountOperation();
        accountOperation.setType(OperationType.CREDIT);
        accountOperation.setAmount(amount);
        accountOperation.setDescription(description);
        accountOperation.setOperationDate(new Date());
        accountOperation.setBankAccount(bankAccount);
        accountOperationRepository.save(accountOperation);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount) {
        debit(accountIdSource,amount,"Transfer to "+accountIdDestination);
        credit(accountIdSource,amount,"Transfer from"+accountIdSource);

    }

    @Override
    public List<BankAccountDTO> bankAccountList()
    {
        List<BankAccount> bankAccounts = bankAccountRepository.findAll();
        List<BankAccountDTO> bankAccountDTOS = bankAccounts.stream().map(bankAccount -> {
            if (bankAccount instanceof SavingAccount) {
                SavingAccount savingAccount = (SavingAccount) bankAccount;
                return dtoMapper.fromSavingBankAccount(savingAccount);
            } else {
                CurrentAccount currentAccount = (CurrentAccount) bankAccount;
                return dtoMapper.fromCurrentBankAccount(currentAccount);
            }
        }).collect(Collectors.toList());
        return bankAccountDTOS;
    }


    // on récupère un customer et on le transforme en customer DTO  
    @Override
    public CustomerDTO getCustomer(Long customerId)
    {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer Not Found!"));
        return  dtoMapper.fromCustomer(customer);
    }
    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        log.info("Saving new Customer");
        Customer customer=dtoMapper.fromCustomerDTO(customerDTO);
        Customer savedCustomer=customerRepository.save(customer);
        return dtoMapper.fromCustomer(savedCustomer);
    }
    @Override
    public void deleteCustomer(Long customerId)
    {
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<AccountOperationDTO> accountHistory(String accountId)
    {
        List<AccountOperation> accountOperations=accountOperationRepository.findAccountOperationByBankAccountId(accountId);

        return accountOperations.stream().map(op->dtoMapper.fromAccountOperation(op)).collect(Collectors.toList());
    }

    @Override
    public AccountHistoryDTO getAccountHistory(String accountId, int page, int size)
    {
        BankAccount bankAccount=bankAccountRepository.findById(accountId).orElse(null);
        if(bankAccount==null) throw new BankAccontNotFoundExeption("Account not Found");
        Page<AccountOperation> accountOperations = accountOperationRepository.findByBankAccountIdOrderByOperationDateDesc(accountId, PageRequest.of(page, size));
        AccountHistoryDTO accountHistoryDTO=new AccountHistoryDTO();
        List<AccountOperationDTO> accountOperationDTOS = accountOperations.getContent().stream().map(op -> dtoMapper.fromAccountOperation(op)).collect(Collectors.toList());
        accountHistoryDTO.setAccountOperationDTOS(accountOperationDTOS);
        accountHistoryDTO.setAccountId(bankAccount.getId());
        accountHistoryDTO.setBalance(bankAccount.getBalance());
        accountHistoryDTO.setCurrentPage(page);
        accountHistoryDTO.setPageSize(size);
        accountHistoryDTO.setTotalPages(accountOperations.getTotalPages());
        return accountHistoryDTO;
    }

    @Override
    public List<CustomerDTO> searchCustomers(String keyword) {
        List<Customer> customers=customerRepository.searchCustomer( keyword);
        List<CustomerDTO> customerDTOS = customers.stream().map(cust -> dtoMapper.fromCustomer(cust)).collect(Collectors.toList());
        return customerDTOS;
    }

}
