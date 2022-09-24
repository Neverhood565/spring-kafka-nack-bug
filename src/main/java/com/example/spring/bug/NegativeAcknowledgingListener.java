package com.example.spring.bug;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.time.Duration;

import static com.example.spring.bug.SpringKafkaNackBugApplication.TOPIC_NAME;

/**
 * Created by romanivanov on 24.09.2022
 */
@Slf4j
@Component
public class NegativeAcknowledgingListener {

    @KafkaListener(topics = TOPIC_NAME, groupId = "default")
    public void onRecord(ConsumerRecord<String, String> record, Acknowledgment ack) {
        log.info("Record received. Offset: {}", record.offset());
        ack.nack(Duration.ofSeconds(5));
        log.info("Negative acknowledged. Sleep for 5 seconds.");
    }
}
