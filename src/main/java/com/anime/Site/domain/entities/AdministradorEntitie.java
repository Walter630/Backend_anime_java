package com.anime.Site.domain.entities;

import lombok.Getter;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class AdministradorEntitie {
    @Id
    private String id;

    private String name;
    private String email;
    private String password;
    private String role;

    public AdministradorEntitie() {
    }

}
