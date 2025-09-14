package com.amitesh.finance_flow.rabbitMQEvent.eventConsumer;

import com.amitesh.finance_flow.rabbitMQEvent.transactionEvent.TransactionCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.amitesh.finance_flow.config.RabbitMQConfig.DASHBOARD_QUEUE;

@Service
public class TransactionEventConsumer {

    @RabbitListener(queues = DASHBOARD_QUEUE)
    public void handleTransactionCreatedEvent(TransactionCreatedEvent event){
        System.out.println(event);
    }
}
