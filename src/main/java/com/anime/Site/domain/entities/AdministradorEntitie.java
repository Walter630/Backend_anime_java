package com.anime.Site.domain.entities;

import lombok.Getter;

import lombok.AllArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class AdministradorEntitie {

    private String id;

    private String name;
    private String email;
    private String password;
    private Boolean isActive;
    private String role;

    public AdministradorEntitie() {
    }

}
