package com.example.inventory.inventory.totalMoneyPerDay;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TotalMoneyPerDayRepo extends JpaRepository<TotalMoneyPerDay,Long> {
   Page<TotalMoneyPerDay> findByDate(LocalDate date, Pageable pageable); // Paginated query

   Page<TotalMoneyPerDay> findAll(Pageable pageable);
}
