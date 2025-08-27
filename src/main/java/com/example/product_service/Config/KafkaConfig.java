package com.example.product_service.Config;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {
    @Bean
    public NewTopic productEventsTopic(@Value("${app.topics.product-events}") String topic) {
        return TopicBuilder.name(topic).partitions(1).replicas(1).build();
    }
}