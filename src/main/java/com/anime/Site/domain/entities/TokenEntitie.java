package com.anime.Site.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;

@Getter
@Setter
@AllArgsConstructor

public class TokenEntitie {

    private String id;
    private String accessToken;      // Token de acesso curto (5–15 min)
    private String refreshToken;     // Token de refresh longo (dias)
    private String email;            // Dono do token

    private Date expiresAt;          // Expiração do accessToken
    private Date expiresRefresh;     // Expiração do refreshToken


    public TokenEntitie() {
    }



}
