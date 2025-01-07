package com.example.inventory.inventory.categories;

import com.example.inventory.inventory.categories.dto.CategoriesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoriesController {
    @Autowired
private    CategoriesService categoriesService;
    @PostMapping("/addCategory")
    public ResponseEntity<?> addCategory(@RequestBody CategoriesDto dto){
    return  new ResponseEntity<>(categoriesService.addCategory(dto), HttpStatus.CREATED);

}

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id, @RequestBody CategoriesDto dto) {
        return new ResponseEntity<>(categoriesService.updateCategory(id, dto), HttpStatus.OK);
    }
    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        return new ResponseEntity<>(categoriesService.deleteCategory(id), HttpStatus.OK);
    }
    @GetMapping("/getAllCategories")
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(categoriesService.getAllCategories(), HttpStatus.OK);
    }
    @GetMapping("/getCategoryById/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        return new ResponseEntity<>(categoriesService.getCategorById(id), HttpStatus.OK);
    }
    @PostMapping("/getCategoryByName")
    public ResponseEntity<?> getCategoryByName(@RequestBody CategoriesDto dto) {
        return new ResponseEntity<>(categoriesService.getCategoryByName(dto), HttpStatus.OK);
    }

}
