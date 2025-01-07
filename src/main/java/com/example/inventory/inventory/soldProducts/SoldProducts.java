package com.example.inventory.inventory.soldProducts;

import com.example.inventory.inventory.products.Products;
import com.example.inventory.inventory.productsInInventory.InventoryProducts;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SoldProducts {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Double discount;
    private String description;
    private Long amount;

    private String barcode;


    private String code;
@CreationTimestamp
private LocalDate date;

    public SoldProducts(Products product) {
        this.name= product.getName();
        this.price= product.getPrice()*product.getAmount();
        this.discount= product.getDiscount();
        this.description= product.getDescription();
        this.amount= product.getAmount();
        this.barcode= product.getBarcode();
        this.code= product.getCode();
        this.date= LocalDate.now();

    }    public SoldProducts(InventoryProducts product) {
        this.name= product.getName();
        this.price= product.getPrice()*product.getAmount();
//        this.discount= product.getDiscount();
        this.description= product.getDescription();
        this.amount= product.getAmount();
        this.barcode= product.getBarcode();
        this.code= product.getCode();
        this.date= LocalDate.now();

    }

}
