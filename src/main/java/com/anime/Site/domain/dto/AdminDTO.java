package com.anime.Site.domain.dto;

import com.anime.Site.domain.entities.AdministradorEntitie;

public record AdminDTO(String email, String senha, String role) {
    public AdminDTO(AdministradorEntitie administradorEntitie) {
        this(administradorEntitie.getEmail(), administradorEntitie.getSenha(), administradorEntitie.getRole());
    }

}
