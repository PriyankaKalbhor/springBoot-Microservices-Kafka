package com.example.orderservice.controller;

import com.example.basedomains.dto.Order;
import com.example.basedomains.dto.OrderEvent;
import com.example.orderservice.kafka.OrderProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class OrderController {

    private final OrderProducerService orderProducerService;

    // Constructor based dependency injection
    public OrderController(OrderProducerService orderProducerService) {
        this.orderProducerService = orderProducerService;
    }

    // @RequestBody ==> This annotation converts Json object into Java Object
   @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order){
        order.setOrderId(UUID.randomUUID().toString());

       OrderEvent orderEvent = new OrderEvent();
       orderEvent.setStatus("PENDING");
       orderEvent.setMessage("Order status is in pending state");
       orderEvent.setOrder(order);

       log.info("Oder event: {}",orderEvent);
       orderProducerService.sendMessage(orderEvent);
       return "Order placed successfully";

   }

}
