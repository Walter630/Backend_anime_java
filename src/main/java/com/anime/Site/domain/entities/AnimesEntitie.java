package com.anime.Site.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter @Setter
@NoArgsConstructor  // For Jackson deserialization
@AllArgsConstructor
public class AnimesEntitie {
    @JsonIgnore  // Hide from Swagger JSON
    private String id = UUID.randomUUID().toString();

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Gênero é obrigatório")
    private String genero;

    @NotBlank(message = "Sinopse é obrigatória")
    private String sinopse;

    @NotBlank(message = "Data de lançamento é obrigatória")
    private String dataLancamento;

    @NotBlank(message = "Status é obrigatório")
    private String status;

    @NotBlank(message = "Imagem é obrigatória")
    private String imagem;

    @NotNull(message = "Favorito é obrigatório")
    private Boolean favorito;

    private List<String> videos = new ArrayList<>();
}
