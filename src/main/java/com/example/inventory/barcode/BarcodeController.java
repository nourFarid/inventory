package com.example.inventory.barcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/barcode")
public class BarcodeController {

    @Autowired
    private BarcodeScannerService barcodeScannerService;

    @PostMapping("/scan")
    public String scanBarcode(@RequestParam("file") MultipartFile file) throws Exception {
        byte[] imageBytes = file.getBytes();
        return barcodeScannerService.decodeBarcode(imageBytes);
    }
}