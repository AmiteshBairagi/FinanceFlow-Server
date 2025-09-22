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
    public static final String EXPENSE_QUEUE = "expense.queue";
    public static final String INCOME_QUEUE = "income.queue";
    public static final String DASHBOARD_ROUTING_KEY = "dashboard.routing.key";
    public static final String INCOME_ROUTING_KEY = "income.routing.key";
    public static final String EXPENSE_ROUTING_KEY = "expense.routing.key";


    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue dashboardQueue(){
        return new Queue(DASHBOARD_QUEUE);
    }

    @Bean
    public Queue incomeQueue(){
        return new Queue(INCOME_QUEUE);
    }

    @Bean
    public Queue expenseQueue(){
        return new Queue(EXPENSE_QUEUE);
    }

    @Bean
    public Binding dashboardQueueBinding(Queue dashboardQueue, TopicExchange topicExchange){
        return BindingBuilder
                .bind(dashboardQueue)
                .to(topicExchange)
                .with(DASHBOARD_ROUTING_KEY);
    }


    @Bean
    public Binding incomeQueueBinding(Queue incomeQueue, TopicExchange topicExchange){
        return BindingBuilder
                .bind(incomeQueue)
                .to(topicExchange)
                .with(INCOME_ROUTING_KEY);
    }


    @Bean
    public Binding expenseQueueBinding(Queue expenseQueue, TopicExchange topicExchange){
        return BindingBuilder
                .bind(expenseQueue)
                .to(topicExchange)
                .with(EXPENSE_ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }


}
