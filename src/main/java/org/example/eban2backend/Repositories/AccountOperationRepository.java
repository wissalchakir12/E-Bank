package org.example.eban2backend.Repositories;

import org.example.eban2backend.Entities.AccountOperation;
import org.example.eban2backend.Entities.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation,Long>
{
    List<AccountOperation> findAccountOperationByBankAccountId(String id);
    Page<AccountOperation> findByBankAccountIdOrderByOperationDateDesc(String id, Pageable page);
}
