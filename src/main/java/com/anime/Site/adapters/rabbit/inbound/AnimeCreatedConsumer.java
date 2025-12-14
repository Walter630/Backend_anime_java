package com.anime.Site.adapters.rabbit.inbound;

import com.anime.Site.adapters.config.configRabbit.ConfigRabbit;
import com.anime.Site.adapters.rabbit.outbound.AnimeCreateEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AnimeCreatedConsumer {

    @RabbitListener(
            queues = ConfigRabbit.QUEUE,
            containerFactory = "simpleRabbitListenerContainerFactory"
    )
    public void receiveMessage(AnimeCreateEvent event) {
        System.out.println(
                "Anime criado (CONSUMER): " +
                        event.getName() +
                        " em " +
                        event.getCreatedAt()
        );
    }

}
