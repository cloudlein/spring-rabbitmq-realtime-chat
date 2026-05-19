package com.demo.chatApp.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String CHAT_EXCHANGE = "chat.exchange";
    public static final String CHAT_QUEUE = "chat.queue.messages";
    public static final String CHAT_ROUTING_KEY = "chat.message.#";

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(CHAT_EXCHANGE);
    }

    @Bean
    public Queue queue() {
        return new Queue(CHAT_QUEUE, true);
    }

    @Bean
    public Binding binding(Queue chatQueue, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(chatQueue)
                .to(topicExchange)
                .with(CHAT_ROUTING_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
