package com.amitesh.finance_flow.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "finance.app.exchange";
    public static final String DASHBOARD_QUEUE = "dashboard.stats.update.queue";
    public static final String ROUTING_KEY = "dashboard.routing.key";


//    public static final String EMAIL_QUEUE = "email.notifications.queue";
//    public static final String NOTIFICATION_QUEUE = "user.notifications.queue";
//    public static final String TRANSACTION_QUEUE = "transactions.event.queue";
//    public static final String BUDGET_QUEUE = "budgets.event.queue";
//    public static final String GOAL_QUEUE = "goals.event.queue";



    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue dashboardQueue(){
        return new Queue(DASHBOARD_QUEUE);
    }

    @Bean
    public Binding binding(Queue dashboardQueue, TopicExchange exchange){
        return BindingBuilder
                .bind(dashboardQueue)
                .to(exchange)
                .with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }


}
