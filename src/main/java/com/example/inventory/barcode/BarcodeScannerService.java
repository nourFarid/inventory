package com.example.inventory.barcode;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;

@Service
public class BarcodeScannerService {

    public String decodeBarcode(byte[] imageBytes) {
        try {
            // Convert byte array to BufferedImage
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(imageBytes);
            BufferedImage bufferedImage = ImageIO.read(byteArrayInputStream);

            // Check if the image is null (in case it's not readable)
            if (bufferedImage == null) {
                throw new IllegalArgumentException("Image could not be read");
            }
            System.out.println("Image size: " + bufferedImage.getWidth() + "x" + bufferedImage.getHeight());
            System.out.println("Image processed: " + (bufferedImage != null ? "Yes" : "No"));

            // Use ZXing to decode the image
            BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
            BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

            // Set barcode formats to check (all supported formats)
            Map<DecodeHintType, Object> hints = new EnumMap<>(DecodeHintType.class);
            hints.put(DecodeHintType.POSSIBLE_FORMATS, EnumSet.allOf(BarcodeFormat.class));

            MultiFormatReader reader = new MultiFormatReader();
            reader.setHints(hints);
            Result result = reader.decode(bitmap);

            // Return the decoded barcode text
            return result.getText();

        } catch (com.google.zxing.NotFoundException e) {
            // Handle case where barcode is not found
            System.err.println("Barcode not found in the image: " + e.getMessage());
            return "Barcode not found";
        } catch (IOException | IllegalArgumentException e) {
            // Handle general image reading or invalid image issues
            System.err.println("Error processing image: " + e.getMessage());
            return "Error processing image";
        } catch (Exception e) {
            // Catch any other unexpected errors
            e.printStackTrace();
            return "Unexpected error occurred";
        }
    }
}
