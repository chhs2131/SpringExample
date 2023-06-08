package com.fcmtutorial.fcmtutorial.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@Slf4j
public class FirebaseConfiguration {
    private static final String FIREBASE_JSON_PATH = "test-388516-firebase-adminsdk-abcdefghijk.json";

    @Bean
    public FirebaseApp initialize() throws IOException {
        ClassPathResource resource = new ClassPathResource(FIREBASE_JSON_PATH);
        GoogleCredentials credentials = GoogleCredentials.fromStream(resource.getInputStream());

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();

        return FirebaseApp.initializeApp(options);

//        log.debug("=== 파이어베이스 초기화 시작 ===");
//        log.debug("FilePath: {}", resource.getDescription());
//        log.debug("FileLength: {}", serviceAccount.available());
//        log.debug("Credentials: {}", credentials.toString());
    }
}
