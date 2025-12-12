package com.anime.Site.domain.entities;

import lombok.*;


import java.time.Instant;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatEntitie {

    private String id;
    private String mensagem;

    private String usuarioId;

    private String animeId;

    private Instant dataCriacao;
    private Instant dataEdicao;
    private Integer like;
    private Integer dislike;
    private Boolean ativo;

    public static ChatEntitie novo(String mensagem, String usuarioId, String animeId) {
       return ChatEntitie.builder()
               .animeId(animeId)
               .usuarioId(usuarioId)
               .mensagem(mensagem)
               .dataCriacao(Instant.now())
               .like(0)
               .dislike(0)
               .ativo(true)
               .build();
    }

}
