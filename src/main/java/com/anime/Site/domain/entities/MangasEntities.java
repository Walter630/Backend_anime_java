package com.anime.Site.domain.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;


@Getter
@Setter
@AllArgsConstructor
public class MangasEntities {
    private String id;
    private String nome;
    private String genero;
    private String sinopse;
    private String dataLancamento;
    private String status;
    private String imagem;

    public MangasEntities() {
    }
}
