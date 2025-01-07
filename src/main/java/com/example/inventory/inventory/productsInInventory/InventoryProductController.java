package com.example.inventory.inventory.productsInInventory;

import com.example.inventory.inventory.products.ProductsService;
import com.example.inventory.inventory.products.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventoryProducts")
public class InventoryProductController {

    @Autowired
    private InventoryProductsService inventoryProductsService;



    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody ProductDto dto) {
        return new ResponseEntity<>(inventoryProductsService.addProduct(dto), HttpStatus.CREATED);
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDto dto) {
        return new ResponseEntity<>(inventoryProductsService.updateProduct(dto), HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return new ResponseEntity<>(inventoryProductsService.deleteProduct(id), HttpStatus.OK);
    }

    @GetMapping("/allProducts")
    public ResponseEntity<?> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(inventoryProductsService.getAllProducts(pageable), HttpStatus.OK);
    }


    @GetMapping("/getProductById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return new ResponseEntity<>(inventoryProductsService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/getProductByBarcode")
    public ResponseEntity<?> getProductByBarcode(@RequestParam String barcode) {
        return new ResponseEntity<>(inventoryProductsService.getProductByBarcode(barcode), HttpStatus.OK);
    }

    @GetMapping("/getProductByName")
    public ResponseEntity<?> getProductByName(@RequestParam String name) {
        return new ResponseEntity<>(inventoryProductsService.getProductByName(name), HttpStatus.OK);
    }
    @GetMapping("/getProductByCode")
    public ResponseEntity<?> getProductByCode(@RequestParam String code) {
        return new ResponseEntity<>(inventoryProductsService.getProductByCode(code), HttpStatus.OK);
    }
    @PostMapping("/getProductInCategory")
    public ResponseEntity<?> getProductInCategory(@RequestBody ProductDto dto) {
        return new ResponseEntity<>(inventoryProductsService.getProductInCategory(dto), HttpStatus.OK);
    }

    @PutMapping("/decreaseAmount/{id}")
    public ResponseEntity<?> decreaseAmount(@PathVariable Long id) {
        return new ResponseEntity<>(inventoryProductsService.decreaseAmount(id), HttpStatus.OK);
    }

    @PutMapping("/increaseAmount/{id}")
    public ResponseEntity<?> increaseAmount(@PathVariable Long id) {
        return new ResponseEntity<>(inventoryProductsService.increaseAmount(id), HttpStatus.OK);
    }
}
