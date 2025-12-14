package com.anime.Site.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
public class AnimesEntitie {
    private String id;

    public AnimesEntitie() {
        this.id = UUID.randomUUID().toString();
    }

    private String name;
    private String genero;
    private String sinopse;
    private String dataLancamento;
    private String status;
    private String imagem;
    private Boolean favorito;

    private List<String> videos = new ArrayList<>();
}
