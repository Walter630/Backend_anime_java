package com.anime.Site.domain.entities;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
public class MangasEntities {
    private String id;

    public MangasEntities() {
        this.id = UUID.randomUUID().toString();
    }
    private String nome;
    private String genero;
    private String sinopse;
    private String dataLancamento;
    private String status;
    private String imagem;

}
