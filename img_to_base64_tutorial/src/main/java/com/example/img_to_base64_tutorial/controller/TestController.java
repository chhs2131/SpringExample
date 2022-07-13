package com.example.img_to_base64_tutorial.controller;

import com.example.img_to_base64_tutorial.util.Base64Converter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Slf4j
@RestController
public class TestController {
    private static final Base64Converter base64Converter = new Base64Converter();

    @GetMapping("/test")
    public String test() {
        return "test123";
    }

    @PostMapping("/file")
    public ResponseEntity<String> save(MultipartFile image) throws IOException {
        String photoImg = base64Converter.fromFile(image);
        return ResponseEntity.ok().body(photoImg);
    }

    @GetMapping("/url")
    public ResponseEntity<String> getArticleImage(
            @RequestParam(value = "image_url")String imageUrl) throws IOException {
        String photoImg = base64Converter.fromImageUrl(imageUrl);
        return ResponseEntity.ok().body(photoImg);
    }
}
