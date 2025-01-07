package com.example.inventory.inventory.transactionsMethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/transactionMethods")
public class TransactionsMethodsController {

    @Autowired
    private TransactionsMethodsService service;


    @PostMapping("/add")
    public ResponseEntity<?> addMethod(@RequestParam String methodName, @RequestParam Double total) {
return  new ResponseEntity<>(service.addMethod(methodName,total),HttpStatus.CREATED);


    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllMethods() {
        return  new ResponseEntity<>(service.getAllMethods(),HttpStatus.OK);

    }


    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getMethodById(@PathVariable Long id) {
        return  new ResponseEntity<>(service.getMethodById(id),HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMethod(@PathVariable Long id, @RequestParam String methodName, @RequestParam Double total) {

        return  new ResponseEntity<>(service.updateMethod(id, methodName, total),HttpStatus.OK);


    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMethod(@PathVariable Long id) {
        return  new ResponseEntity<>(service.deleteMethod(id),HttpStatus.OK);


    }


    @GetMapping("/search")
    public ResponseEntity<?> findMethodByName(@RequestParam String methodName) {
        return  new ResponseEntity<>(service.findMethodByName(methodName),HttpStatus.OK);
    }
}
