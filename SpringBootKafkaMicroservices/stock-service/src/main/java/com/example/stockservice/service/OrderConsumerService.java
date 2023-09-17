package com.example.stockservice.service;

import com.example.basedomains.dto.OrderEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderConsumerService {


    @KafkaListener(topics = "${spring.kafka.topic.name}"
    , groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent){

        log.info("Order event received in stock service consumer: {}", orderEvent.toString());

        // TODO : Save OrderEvent data into Database

    }
}
