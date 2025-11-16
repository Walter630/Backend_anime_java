package com.anime.Site.domain.entities;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Document(collection = "Chat")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatEntitie {
    @Id
    private String id;
    private String mensagem;
    // id do usuário que comentou
    @Indexed
    private String usuarioId;
    // id do anime que recebeu o comentário
    @Indexed
    private String animeId;
    // data de criação (UTC)
    @Indexed
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
