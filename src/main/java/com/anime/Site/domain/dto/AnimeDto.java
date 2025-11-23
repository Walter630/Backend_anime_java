package com.anime.Site.domain.dto;


import com.anime.Site.domain.entities.AnimesEntitie;

import java.util.List;

public record AnimeDto(String nome, String genero, String sinopse, String status, String dataLancamento, String imagem,
                       Boolean favorito, List<String> videos) {
    public AnimeDto(AnimesEntitie animesEntitie) {
        this(animesEntitie.getNome(), animesEntitie.getGenero(), animesEntitie.getSinopse(), animesEntitie.getStatus(), animesEntitie.getDataLancamento(), animesEntitie.getImagem(), animesEntitie.getFavorito(), animesEntitie.getVideos());
    }
}
