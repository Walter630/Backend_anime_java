package com.anime.Site.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VideoEntitie {
    private String id;
    private String animeId; // ID do anime dono desse episódio
    private Integer numero; // Episódio 1, 2, 3...
    private String titulo;  // opcional
    private String videoUrl; // link direto do Telegram

    public VideoEntitie() {
    }
}
