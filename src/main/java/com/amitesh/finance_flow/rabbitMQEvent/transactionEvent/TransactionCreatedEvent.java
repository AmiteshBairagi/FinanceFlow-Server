package com.amitesh.finance_flow.rabbitMQEvent.transactionEvent;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionCreatedEvent {
    private String transactionId;
    private String userId;
    private double amount;
}
