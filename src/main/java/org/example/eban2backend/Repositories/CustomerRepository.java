package org.example.eban2backend.Repositories;

import org.example.eban2backend.Entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
