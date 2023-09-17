package com.example.orderservice.kafka;

import com.example.basedomains.dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderProducerService {

    private final NewTopic newTopic;

    // We add dependency of Base-Domain to get OrderEvent
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;


    public OrderProducerService(NewTopic newTopic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.newTopic = newTopic;
        this.kafkaTemplate = kafkaTemplate;
    }

    // Create sendMessage() custom method
    public void sendMessage(OrderEvent orderEvent) {

        log.info("Order event => {}", orderEvent.toString());

        // Create Message
        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, newTopic.name())
                .build();

        // Send message to kafka topic using KafkaTemplate send()
        kafkaTemplate.send(message);
    }
}
