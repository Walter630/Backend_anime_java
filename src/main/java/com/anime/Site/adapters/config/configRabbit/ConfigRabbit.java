package com.anime.Site.adapters.config.configRabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigRabbit {

    public static final String QUEUE = "anime.created.queue";
    public static final String EXCHANGE = "anime.exchange";
    public static final String ROUTING_KEY = "anime.created";

    @Bean
    public Queue animeCreatedQueue() {
        return new Queue(QUEUE, true);
    }

    @Bean
    public TopicExchange createExchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(animeCreatedQueue())
                .to(createExchange())
                .with(ROUTING_KEY);
    }
}
