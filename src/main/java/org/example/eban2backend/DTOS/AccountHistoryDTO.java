package org.example.eban2backend.DTOS;

import lombok.Data;

import java.util.List;
@Data

public class AccountHistoryDTO
{
    private String accountId;
    private double balance;
    private int currentPage;
    private int TotalPages;
    private int pageSize;
    private List<AccountOperationDTO> accountOperationDTOS;

}
