package com.example.img_to_base64_tutorial.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.net.URL;
import java.util.Base64;

public class Base64Converter {
    public Base64Converter() {
    }

    /**
     * 파일형태의 이미지를 Base64로 반환합니다.
     * @param image 파일 형태의 이미지
     * @return Base64 코드 (String)
     * @throws IOException
     */
    public String fromFile(MultipartFile image) throws IOException {
        String photoImg = null;
        if (image != null) {
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] photoEncode = encoder.encode(image.getBytes());
            photoImg = new String(photoEncode, "UTF8");
        }
        return photoImg;
    }

    /**
     * URL 형태의 이미지를 Base64로 반환합니다.
     * @param imageUrl URL 형태의 이미지
     * @return Base64 코드 (String)
     * @throws IOException
     */
    public String fromImageUrl(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);

        InputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1!=(n=in.read(buf))) {
            out.write(buf, 0, n);
        }
        out.close();
        in.close();
        byte[] response = out.toByteArray();

        Base64.Encoder encoder = Base64.getEncoder();
        byte[] photoEncode = encoder.encode(response);
        String photoImg = new String(photoEncode, "UTF8");

        return photoImg;
    }
}
