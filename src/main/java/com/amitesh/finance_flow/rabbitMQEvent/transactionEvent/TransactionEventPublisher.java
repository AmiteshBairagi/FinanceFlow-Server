package com.amitesh.finance_flow.rabbitMQEvent.transactionEvent;


import com.amitesh.finance_flow.config.RabbitMQConfig;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionEventPublisher {

    private final AmqpTemplate amqpTemplate;

    public TransactionEventPublisher(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }

    public String publishTransactionCreatedEvent (String transactionId, String userId, double amount){
        TransactionCreatedEvent event = new TransactionCreatedEvent(transactionId,userId,amount);

        amqpTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME,RabbitMQConfig.ROUTING_KEY,event);
        System.out.println("[Publisher] Event Published: " + event.getTransactionId());

        return "Transaction Event Published";
    }
}
