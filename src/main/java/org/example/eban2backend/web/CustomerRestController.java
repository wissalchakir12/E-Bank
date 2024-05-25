package org.example.eban2backend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.eban2backend.DTOS.AccountOperationDTO;
import org.example.eban2backend.DTOS.CustomerDTO;
import org.example.eban2backend.Entities.Customer;
import org.example.eban2backend.Services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j

public class CustomerRestController
{
    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    public List<CustomerDTO> customers(){
        return bankAccountService.listCustomers();
    }


    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable(name="id")Long customerId)
    {
        return bankAccountService.getCustomer(customerId);
    }

    @PostMapping("/customers")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO)//indique qu'on va recuperer les données du customer à partir du corps de la requette sous format JSON
    {
        return bankAccountService.saveCustomer(customerDTO);

    }

    @PutMapping("/customers/{customerId}")
    public  CustomerDTO updateCustomer(@PathVariable Long customerId, @RequestBody  CustomerDTO customerDTO)
    {
        customerDTO.setId(customerId);
        return bankAccountService.updateCustomer(customerDTO);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable Long id)
    {
        bankAccountService.deleteCustomer(id);
    }


}