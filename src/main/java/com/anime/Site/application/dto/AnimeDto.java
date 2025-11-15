package com.anime.Site.application.dto;


import com.anime.Site.domain.entities.AnimesEntitie;

import java.util.Date;

public record AnimeDto(String nome, String genero, String sinopse, String status, String dataLancamento, String imagem) {
    public AnimeDto(AnimesEntitie animesEntitie) {
        this(animesEntitie.getNome(), animesEntitie.getGenero(), animesEntitie.getSinopse(), animesEntitie.getStatus(), animesEntitie.getDataLancamento(), animesEntitie.getImagem());
    }
}
