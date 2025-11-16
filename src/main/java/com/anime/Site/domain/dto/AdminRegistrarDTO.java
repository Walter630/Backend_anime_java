package com.anime.Site.domain.dto;

public record AdminRegistrarDTO(String nome, String email, String senha) {
    // Só email e senha, role será setada como ADMIN
}
