package com.amitesh.finance_flow.rabbitMQEvent.transactionEvent;


import com.amitesh.finance_flow.config.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class ExpenseAddedEventPublisher {

    private final AmqpTemplate amqpTemplate;

    public ExpenseAddedEventPublisher(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public String publishExpenseAddedEvent (String userId, String categoryId, double amountSpent){
        ExpenseAddedEvent event = ExpenseAddedEvent.builder()
                        .userId(userId)
                                .categoryId(categoryId)
                                        .amountSpent(amountSpent)
                                                .build();


        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.EXPENSE_ROUTING_KEY,event);
        System.out.println("Expense Added Event Published Successfully");

        return "Expense Added Event Published Successfully";
    }
}
