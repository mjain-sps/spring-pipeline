package com.community.client.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RestController
public class ImageUploadController {

    //Create a post method which accepts image file
    @PostMapping("/api/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam MultipartFile image) throws IOException {
        //Basic validations related to the image
        //Check if the uploaded image is empty
        if (image.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot upload empty image");
        }

        //Check if the image is of right type
        Object imageObject = image.getContentType();
        System.out.println(imageObject);
//        if (imageObject != null && !imageObject.toString().equals("image/jpeg") || imageObject != null && !imageObject.toString().equals("image/jpg")) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Only JPEG/JPG uploads allowed");
//        }

        //check if the image size is within specified range
        long imageSize = image.getSize();
        //greater than 5MB, 1 MB has 1000000 BYTES
        if (imageSize > 5000000) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can upload images less than 5MB only");
        }

        //Method body to execute if everything is okay and good to upload
        String uploadDir = "src/main/resources/static/uploads";
        try {
            Files.copy(image.getInputStream(), Paths.get(uploadDir + File.separator + image.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            return ResponseEntity.status(HttpStatus.OK).body(image.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage() + "\n File Could be Uploaded. Please tray again later.");
        }
    }
}
