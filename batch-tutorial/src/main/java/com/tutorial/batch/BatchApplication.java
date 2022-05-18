package com.tutorial.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableBatchProcessing  // 배치Batch 기능 활성화
@SpringBootApplication
public class BatchApplication {
	// Spring Batch 가이드 : https://jojoldu.tistory.com/326?category=902551
	public static void main(String[] args) {
		SpringApplication.run(BatchApplication.class, args);
	}

}
