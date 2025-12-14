package com.anime.Site.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
public class AdministradorEntitie {

    private String id;

    public AdministradorEntitie() {
        this.id = UUID.randomUUID().toString();
    }
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
    private String role;

}
