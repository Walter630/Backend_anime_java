package com.anime.Site.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;

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

    public UserEntitie() {
    }

}
