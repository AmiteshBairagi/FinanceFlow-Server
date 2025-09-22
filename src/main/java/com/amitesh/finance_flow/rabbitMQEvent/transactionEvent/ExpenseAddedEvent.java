package com.amitesh.finance_flow.rabbitMQEvent.transactionEvent;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseAddedEvent {
    private String userId;
    private String categoryId;
    private double amountSpent;
}
