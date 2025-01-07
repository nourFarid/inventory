package com.example.inventory.inventory.categories;

import com.example.inventory.inventory.products.Products;
import com.example.inventory.inventory.productsInInventory.InventoryProducts;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categories {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonBackReference

    private List<Products> products=new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "inventory_product_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonBackReference

    private List<InventoryProducts> inventoryProducts=new ArrayList<>();
    public Categories(String name) {
        this.name = name;
    }
}
