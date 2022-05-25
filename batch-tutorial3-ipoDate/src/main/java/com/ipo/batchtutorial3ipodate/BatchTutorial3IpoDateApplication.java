package com.ipo.batchtutorial3ipodate;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing  // 배치기능 활성화
public class BatchTutorial3IpoDateApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchTutorial3IpoDateApplication.class, args);
    }

}
