package com.example.spring.bug;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.listener.ContainerProperties;

import static org.springframework.kafka.listener.ContainerProperties.AckMode.MANUAL;

@SpringBootApplication
public class SpringKafkaNackBugApplication {

    public static final String TOPIC_NAME = "simple_topic";

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
            ConsumerFactory<String, String> consumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<String, String> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.getContainerProperties().setAckMode(MANUAL);
        factory.getContainerProperties().setAsyncAcks(true);
        return factory;
    }

    @Bean
    KafkaAdmin.NewTopics createTopics() {
        return new KafkaAdmin.NewTopics(new NewTopic(TOPIC_NAME, 1, (short) 1));
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaNackBugApplication.class, args);
    }
}
