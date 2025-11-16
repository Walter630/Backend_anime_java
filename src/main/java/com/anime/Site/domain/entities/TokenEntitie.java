package com.anime.Site.domain.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "Token")
public class TokenEntitie {
    @Id
    private String id;
    private String token;

    public TokenEntitie() {
    }



}
