package com.example.embedded_kafka;

import com.example.embedded_kafka.core.KafkaConsumer;
import com.example.embedded_kafka.core.KafkaProducer;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.example.embedded_kafka.core.KafkaConsumer.TOPIC_NAME;

@SpringBootTest
@EmbeddedKafka(partitions = 2, brokerProperties = {"listeners=PLAINTEXT://localhost:9092"}, ports = {9092})
public class EmbeddedKafkaTest {

    @Autowired
    KafkaProducer kafkaProducer;

    @Autowired
    KafkaConsumer kafkaConsumer;

    @Test
    void test() throws Exception {
        // given
        String topic = TOPIC_NAME;
        String payload = "hello! 안녕하세요? 1234567890 !@#$%^&*()";

        // when
        kafkaProducer.send(topic, payload);

        // then
        Awaitility.await()
                .atMost(30, TimeUnit.SECONDS)  // 30초 이내에 아래 테스트 조건을 만족하지 못하면 실패한다.
                .untilAsserted(() -> {
                    List<String> messages = kafkaConsumer.getMessages();
                    Assertions.assertThat(messages).contains("hello! 안녕하세요? 1234567890 !@#$%^&*()");
                });
    }

}
