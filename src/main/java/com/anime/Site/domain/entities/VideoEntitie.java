package com.anime.Site.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Videos")
@Getter
@Setter
@AllArgsConstructor
public class VideoEntitie {
    @Id
    private String id;

    private String animeId; // ID do anime dono desse episódio
    private Integer numero; // Episódio 1, 2, 3...
    private String titulo;  // opcional
    private String videoUrl; // link direto do Telegram

    public VideoEntitie() {
    }
}
