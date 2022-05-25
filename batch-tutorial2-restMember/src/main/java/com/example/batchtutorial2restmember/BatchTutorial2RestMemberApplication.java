package com.example.batchtutorial2restmember;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class BatchTutorial2RestMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(BatchTutorial2RestMemberApplication.class, args);
    }

}
