
package com.anime.Site.adapters.controller;

import com.anime.Site.adapters.config.configRabbit.ConfigRabbit;
import com.anime.Site.domain.dto.ChatRequestDTO;
import com.anime.Site.domain.entities.ChatEntitie;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ChatWebSocketController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * O front envia para /app/chat.send
     * Exemplo no front:
     * stompClient.send("/app/chat.send", {}, JSON.stringify(mensagem));
     */
    @MessageMapping("/chat.send")
    public void sendMessage(ChatRequestDTO chatDTO) {
        // O DTO enviado pelo WebSocket vira mensagem enviada ao Rabbit
        ChatEntitie entity = new ChatEntitie(
                null,
                chatDTO.mensagem(),
                chatDTO.usuarioId(),
                chatDTO.animeId(),
                null,
                null,
                0,
                0,
                true
        );

        // Envia para o RabbitMQ → Será consumido pelo ChatListener
        rabbitTemplate.convertAndSend(
                ConfigRabbit.EXCHANGE,
                ConfigRabbit.ROUTING_KEY,
                entity
        );
    }
}
