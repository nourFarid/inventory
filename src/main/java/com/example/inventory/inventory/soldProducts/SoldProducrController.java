package com.example.inventory.inventory.soldProducts;

import com.example.inventory.inventory.products.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class SoldProducrController {

    @Autowired
    private SoldProductsService soldProductsService;

    @GetMapping("/getSoldProducts")
    public ResponseEntity<?> getSoldProducts(
            @RequestParam(defaultValue = "0") int page, // Default to first page
            @RequestParam(defaultValue = "5") int size // Default to 5 items per page
    ) {
        return new ResponseEntity<>(soldProductsService.getSoldProducts(page, size), HttpStatus.OK);
    }
    @GetMapping("/getSoldProductsByDate")
    public ResponseEntity<?> getSoldProductsByDate(@RequestParam String date
    ) {
        return new ResponseEntity<>(soldProductsService.getSoldProductsByDate(date), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> getSoldProductsByDate(@PathVariable Long id
    ) {
        return new ResponseEntity<>(soldProductsService.delete(id), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody ProductDto dto
                                    ) {
        return new ResponseEntity<>(soldProductsService.update(dto), HttpStatus.OK);
    }


}
