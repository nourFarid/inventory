package com.example.inventory.inventory.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionsController {

    @Autowired
    private TransactionsService service;

    // Add a new transaction
    @PostMapping("/add")
    public ResponseEntity<?> addTransaction(@RequestBody TransactionDto dto) {
        return new ResponseEntity<>(service.add(dto), HttpStatus.CREATED);
    }

    // Retrieve all transactions
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllTransactions(
            @RequestParam(defaultValue = "0") int page, // default page is 0
            @RequestParam(defaultValue = "10") int size // default size is 10
    ) {
        return new ResponseEntity<>(service.getAllTransactions(page, size), HttpStatus.OK);
    }

    // Retrieve a single transaction by ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getTransactionById(id), HttpStatus.OK);
    }

    // Update an existing transaction
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto dto) {
        return new ResponseEntity<>(service.updateTransaction(id, dto), HttpStatus.OK);
    }

    // Delete a transaction by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable Long id) {
        return new ResponseEntity<>(service.deleteTransaction(id), HttpStatus.OK);
    }

    // Retrieve all debit transactions
    @GetMapping("/getDebits")
    public ResponseEntity<?> getDebitTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(service.getDebit(page, size), HttpStatus.OK);
    }
    // Retrieve all credit transactions
    @GetMapping("/getCredits")
    public ResponseEntity<?> getCreditTransactions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(service.getCredit(page, size), HttpStatus.OK);
    }


    @GetMapping("/getByDate")
    public ResponseEntity<?> getByDate(
            @RequestParam String date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(service.getByDate(date, page, size), HttpStatus.OK);
    }


}
