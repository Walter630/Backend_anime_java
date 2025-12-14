package com.anime.Site.adapters.rabbit.outbound;

import com.anime.Site.adapters.config.configRabbit.ConfigRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AnimeCreateProducer {
    private final RabbitTemplate rabbitTemplate;

    public AnimeCreateProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void send(AnimeCreateEvent event) {
        System.out.println("Anime criado: " + event.getName() + " em " + event.getCreatedAt() + "send");
        event.setCreatedAt(LocalDateTime.now());
        rabbitTemplate.convertAndSend(
                ConfigRabbit.EXCHANGE,
                ConfigRabbit.ROUTING_KEY,
                event
        );
    }
}
