package com.example.springbootautoconfigurationexample;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionEvaluationReport;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootAutoconfigurationExampleApplication {
    // Boot Application이 시작되고 난 후에 동작하는 빈
    // Conditional Evaluation Report를 확인해서 각 Bean들에 대한 매칭여부를 체크한다.
    @Bean
    ApplicationRunner run(ConditionEvaluationReport report) {
        return args -> System.out.println("Bean갯수: " +
            report.getConditionAndOutcomesBySource()
                    .entrySet()
                    .stream()
                    .filter(co -> co.getValue().isFullMatch())
                    .map(co -> {
                        System.out.println(co.getKey());
                        co.getValue().forEach(c -> {
                            System.out.println("\t" + c.getOutcome());
                        });
                        System.out.println();
                        return co;
                    }).count());
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAutoconfigurationExampleApplication.class, args);
    }

}
