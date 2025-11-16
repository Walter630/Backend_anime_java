package com.anime.Site.adapters.services;

import com.anime.Site.adapters.config.configRabbit.ConfigRabbit;
import com.anime.Site.adapters.repository.ChatRepository;
import com.anime.Site.domain.dto.ChatRequestDTO;
import com.anime.Site.domain.entities.ChatEntitie;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public List<ChatEntitie> findAll() {
        return chatRepository.findAll();
    }

    public ChatEntitie save(ChatRequestDTO dto) {

        ChatEntitie entity = ChatEntitie.novo(
                dto.mensagem(),
                dto.usuarioId(),
                dto.animeId()
        );

        ChatEntitie saved = chatRepository.save(entity);

        rabbitTemplate.convertAndSend(
                ConfigRabbit.EXCHANGE,
                ConfigRabbit.ROUTING_KEY,
                saved
        );

        return saved;
    }
}
