package com.anime.Site.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
public class UserEntitie {
    private String id;

    public UserEntitie() {
        this.id = UUID.randomUUID().toString();
    }
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
    private String role;


    private List<String> favoritos = new ArrayList<>();

    @Override
    public String toString() {
        return "UserEntitie [id=" + id + ", nome=" + name + ", email=" + email + ", senha=" + password + ", role=" + role + ", ativo=" + isActive
                + ", favoritos=" + favoritos + "]";
    }

    public List<String> getFavoritos() {
        return favoritos;
    }
}
