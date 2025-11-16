package com.anime.Site.adapters.controller;

import com.anime.Site.adapters.services.ChatService;
import com.anime.Site.domain.dto.ChatRequestDTO;
import com.anime.Site.domain.entities.ChatEntitie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping("/salvar")
    public ChatEntitie save(@RequestBody ChatRequestDTO chatRequestDTO){
        return chatService.save(chatRequestDTO);
    }

    @GetMapping("/listar")
    public List<ChatEntitie> findAll(){
        return chatService.findAll();
    }
}
