package com.example.inventory.inventory.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories,Long> {
    Categories findByName(String name);
}
