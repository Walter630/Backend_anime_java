package com.anime.Site.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class VideoEntitie {
    private String id;

    public VideoEntitie() {
        this.id = UUID.randomUUID().toString();
    }
    private String animeId; // ID do anime dono desse episódio
    private Integer numero; // Episódio 1, 2, 3...
    private String titulo;  // opcional
    private String videoUrl; // link direto do Telegram


}
