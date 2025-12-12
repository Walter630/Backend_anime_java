package com.anime.Site.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
public class UserEntitie {
    private String id;
    private String name;
    private String email;
    private String password;
    private Boolean isActive;
    private String role;


    private List<String> favoritos = new ArrayList<>();
    public UserEntitie() {
    }

    @Override
    public String toString() {
        return "UserEntitie [id=" + id + ", nome=" + name + ", email=" + email + ", senha=" + password + ", role=" + role + ", ativo=" + isActive
                + ", favoritos=" + favoritos + "]";
    }

    public List<String> getFavoritos() {
        return favoritos;
    }
}
