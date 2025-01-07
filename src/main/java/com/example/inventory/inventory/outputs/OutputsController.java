package com.example.inventory.inventory.outputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/outputs")
public class OutputsController {

    @Autowired
    private OutputsService outputsService;

    @PostMapping("/addOutput")
    public ResponseEntity<?> addOutput(@RequestParam String output, @RequestParam Double value) {
        return new ResponseEntity<>(outputsService.addOutputs(output, value), HttpStatus.CREATED);
    }

    @PutMapping("/updateOutput/{id}")
    public ResponseEntity<?> updateOutput(@PathVariable Long id,
                                          @RequestParam(required = false) String output,
                                          @RequestParam(required = false) Double value) {
        return new ResponseEntity<>(outputsService.updateOutput(id, output, value), HttpStatus.OK);
    }

    @DeleteMapping("/deleteOutput/{id}")
    public ResponseEntity<?> deleteOutput(@PathVariable Long id) {
        return new ResponseEntity<>(outputsService.deleteOutput(id), HttpStatus.OK);
    }

    @GetMapping("/allOutputs")
    public ResponseEntity<?> getAllOutputs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(outputsService.getAll(pageable), HttpStatus.OK);
    }


}
