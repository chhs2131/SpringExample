package com.example.batchtutorial2restmember.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class TestJobConfig {
    @Bean
    public JobLauncherTestUtils jobLancherTestUtils() {
        return new JobLauncherTestUtils();
    }
}
