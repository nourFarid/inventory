package com.example.inventory.inventory.products.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private Double price;
    private Double discount;
    private String description;
    private Long amount;
    private String barcode;
    private String code;
    List<Long> categoriesId;
    private Boolean isShop;
    private Boolean isInventory;
}
