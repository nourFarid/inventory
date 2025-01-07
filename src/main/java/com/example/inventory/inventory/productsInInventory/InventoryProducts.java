package com.example.inventory.inventory.productsInInventory;

import com.example.inventory.inventory.categories.Categories;
import com.example.inventory.inventory.products.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.EnumNaming;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InventoryProducts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)

    private String name;
    private Double price;
//    private Double discount;
    private String description;
    private Long amount;
    @Column(unique = true)
    private String barcode;
    private String code;
    @ManyToMany(mappedBy = "inventoryProducts")
    @JsonManagedReference

    private List<Categories> categories = new ArrayList<>();
    @CreationTimestamp
    private LocalDate date;
    public InventoryProducts(ProductDto dto) {
        this.name = dto.getName();
        this.price = dto.getPrice();
//        this.discount = dto.getDiscount();
        this.description = dto.getDescription();
        this.amount = dto.getAmount();
        this.barcode = dto.getBarcode();
        this.code = dto.getCode();
        this.date = LocalDate.now();
    }
}
