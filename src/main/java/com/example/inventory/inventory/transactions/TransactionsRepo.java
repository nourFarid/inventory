package com.example.inventory.inventory.transactions;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionsRepo extends JpaRepository<Transactions,Long> {
    Page<Transactions> findAll(Pageable pageable);
    Page<Transactions> findAllByIsDebit(Boolean isDebit, Pageable pageable);
    Page<Transactions> findAllByDate(LocalDate date, Pageable pageable);
}
