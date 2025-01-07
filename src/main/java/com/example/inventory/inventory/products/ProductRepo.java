package com.example.inventory.inventory.products;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Products,Long> {
    List<Products> findByNameContainingIgnoreCase(String name);
    Products findByBarcode(String barcode);
    Page<Products> findAll(Pageable pageable);

    @Query("SELECT p FROM Products p JOIN p.categories c WHERE c.id IN :ids")
    List<Products> findAllByCategories(@Param("ids") List<Long> ids);

  List<Products> findByCodeContainingIgnoreCase(String code);
}
