package com.anime.Site.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;

@Document(collection = "Animes")
@Getter
@Setter
@AllArgsConstructor
public class AnimesEntitie {
    @Id
    private String id;
    private String nome;
    private String genero;
    private String sinopse;
    private String dataLancamento;
    private String status;
    private String imagem;

    public AnimesEntitie() {
    }
}
