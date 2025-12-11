package com.anime.Site.domain.entities;


import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnimesEntitie {
    private Long id;

    private String nome;
    private String genero;
    private String sinopse;
    private String dataLancamento;
    private String status;
    private String imagem;
    private Boolean favorito;

    private List<String> videos = new ArrayList<>();
}
