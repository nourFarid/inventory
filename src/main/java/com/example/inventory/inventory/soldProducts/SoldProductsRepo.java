package com.example.inventory.inventory.soldProducts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SoldProductsRepo extends JpaRepository<SoldProducts,Long> {

    List<SoldProducts> findAllByDate(LocalDate date);
}
