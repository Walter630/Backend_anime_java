package com.anime.Site.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

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
    private Boolean favorito;
    private List<String> videos; // ← episódios do anime

    public AnimesEntitie() {
        this.videos = new ArrayList<>();
    }

}
