package com.example.basedomains.dto;

// This class is used to transfer the data between producer and consumer

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {

    private String message;

    private String status;

    private Order order;
}
