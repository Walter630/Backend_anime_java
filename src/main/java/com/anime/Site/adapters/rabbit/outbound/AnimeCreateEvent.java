package com.anime.Site.adapters.rabbit.outbound;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AnimeCreateEvent {
    private String animeId;

    public AnimeCreateEvent() {
    }

    private String name;
    private LocalDateTime createdAt;

    public AnimeCreateEvent(String animeId, String name) {
        this.animeId = animeId;
        this.name = name;
    }
}
