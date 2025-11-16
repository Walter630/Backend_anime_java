package com.anime.Site.adapters;

import com.anime.Site.adapters.config.configRabbit.ConfigRabbit;
import com.anime.Site.domain.entities.ChatEntitie;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class ChatListener {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RabbitListener(queues = ConfigRabbit.QUEUE)
    public void receiveMessage(ChatEntitie chatEntitie){
        messagingTemplate.convertAndSend("/topic/chat/" + chatEntitie.getAnimeId(), chatEntitie);
    }
}
