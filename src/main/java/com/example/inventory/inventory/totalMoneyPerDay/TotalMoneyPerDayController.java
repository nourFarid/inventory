package com.example.inventory.inventory.totalMoneyPerDay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/totalMoneyPerDay")

public class TotalMoneyPerDayController {

@Autowired
    private TotalMoneyPerDayService totalMoneyPerDayService;


    @PostMapping("/add")
    public ResponseEntity<?> addTotalMoneyPerDay(@RequestParam Double totalMoney, @RequestParam String source) {
        return new ResponseEntity<>(totalMoneyPerDayService.addTotalMoneyPerDay(totalMoney,source), HttpStatus.CREATED);
    }

    @GetMapping("/getByDate")
    public ResponseEntity<?> getTotalMoneyPerDayByDate(
            @RequestParam String date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return new ResponseEntity<>(totalMoneyPerDayService.getTotalMoneyPerDayByDate(date, page, size), HttpStatus.OK);
    }

    @GetMapping("/getAllTotalMoney")
    public ResponseEntity<?> getAllTotalMoney(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(totalMoneyPerDayService.getAllTotalMoney(pageable), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTotalMoney(@PathVariable Long id, @RequestParam Double totalMoney) {
        return new ResponseEntity<>(totalMoneyPerDayService.updateTotalMoney(id, totalMoney), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTotalMoney(@PathVariable Long id) {
        return new ResponseEntity<>(totalMoneyPerDayService.deleteTotalMoney(id), HttpStatus.OK);
    }
    @GetMapping("/getTotalMoneyPerDayById/{id}")
    public ResponseEntity<?> getTotalMoneyPerDayById(@PathVariable Long id) {
        return new ResponseEntity<>(totalMoneyPerDayService.getTotalMoneyPerDayById(id), HttpStatus.OK);
    }
}
