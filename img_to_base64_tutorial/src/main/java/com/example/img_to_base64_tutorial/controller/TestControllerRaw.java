package com.example.img_to_base64_tutorial.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Base64;

@Slf4j
@RestController
public class TestControllerRaw {
    @GetMapping("/raw/test")
    public String test() {
        return "test123";
    }

    @PostMapping("/raw/file")
    public ResponseEntity<String> save(MultipartFile image) throws IOException {
        String photoImg = null;
        if (image != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] photoEncode = encoder.encode(image.getBytes());
            photoImg = new String(photoEncode, "UTF8");
        }
        return ResponseEntity.ok().body(photoImg);
    }

    @GetMapping("/raw/url")
    public ResponseEntity<String> getArticleImage(
            @RequestParam(value = "image_url")String imageUrl) throws IOException {
        log.info("Requested picture >> " + imageUrl + " <<");

        URL url = new URL(imageUrl);
        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1!=(n=in.read(buf)))
        {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] photoEncode = encoder.encode(response);
        String photoImg = new String(photoEncode, "UTF8");

        return ResponseEntity.ok().body(photoImg);
    }
}
