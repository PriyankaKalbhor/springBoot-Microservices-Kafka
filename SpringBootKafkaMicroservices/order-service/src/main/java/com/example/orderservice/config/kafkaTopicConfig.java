package com.example.orderservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class kafkaTopicConfig {

    // Get topic name from application.properties file
    @Value("${spring.kafka.topic.name}")
    private String topicName;


    // Spring bean for kafka topic
    // Here we can also mention the partition how many partitions we want to create
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(topicName)
                .build();
    }


}
