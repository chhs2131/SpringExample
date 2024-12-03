package com.example.embedded_kafka.core;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaConsumer {

    public static final String TOPIC_NAME = "testTopic";
    private final List<String> stringRepository = new CopyOnWriteArrayList<>();

    @KafkaListener(topics = TOPIC_NAME, groupId = "testGroup")
    protected void consume(@Payload String payload, Acknowledgment acknowledgment) throws Exception {
        log.info("receive event: {}", payload);
        stringRepository.add(payload);

        acknowledgment.acknowledge();
    }

    public List<String> getMessages() {
        return Collections.unmodifiableList(stringRepository);
    }

}
