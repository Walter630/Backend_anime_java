package com.anime.Site.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "User")
@Setter
@Getter
@AllArgsConstructor

public class UserEntitie {
    @Id
    private String id;
    private String nome;
    private String email;
    private String senha;
    private String role;

    private List<String> favoritos = new ArrayList<>();
    public UserEntitie() {
    }

    @Override
    public String toString() {
        return "UserEntitie [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", role=" + role
                + ", favoritos=" + favoritos + "]";
    }

    public List<String> getFavoritos() {
        return favoritos;
    }
}
