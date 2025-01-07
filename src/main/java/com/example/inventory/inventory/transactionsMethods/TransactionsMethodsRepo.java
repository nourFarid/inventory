package com.example.inventory.inventory.transactionsMethods;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsMethodsRepo extends JpaRepository<TransactionsMethods,Long> {
 List< TransactionsMethods> findByMethodNameContainingIgnoreCase(String methodName);
}
