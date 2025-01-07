package com.example.inventory.inventory.productsInInventory;

import com.example.inventory.inventory.products.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryProductRepo extends JpaRepository<InventoryProducts,Long> {
    List<InventoryProducts> findByNameContainingIgnoreCase(String name);
    InventoryProducts findByBarcode(String barcode);
    Page<InventoryProducts> findAll(Pageable pageable);


    @Query("SELECT p FROM InventoryProducts p JOIN p.categories c WHERE c.id IN :ids")
    Page<InventoryProducts> findAllByCategories(@Param("ids") List<Long> ids, Pageable pageable);

  List<InventoryProducts> findByCodeContainingIgnoreCase(String code);
}
