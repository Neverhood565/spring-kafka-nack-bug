package com.example.spring.bug;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.IntStream;

import static com.example.spring.bug.SpringKafkaNackBugApplication.TOPIC_NAME;

/**
 * Created by romanivanov on 24.09.2022
 */
@Component
@RequiredArgsConstructor
public class TestDataGenerator {

    private final KafkaTemplate<String, String> template;

    @EventListener(value = ApplicationReadyEvent.class)
    public void onApplicationReadyEvent() {
        IntStream.range(0, 10)
                 .forEach(ignored -> template.send(TOPIC_NAME, "Some data", UUID.randomUUID().toString()));
    }
}
