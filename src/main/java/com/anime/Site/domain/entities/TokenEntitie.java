package com.anime.Site.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class TokenEntitie {
    private String id = UUID.randomUUID().toString(); // ✅ Default no campo
    private String accessToken;
    private String refreshToken;
    private String email;
    private Date expiresAt;
    private Date expiresRefresh;

    // Construtor vazio SEM sobrescrever id
    public TokenEntitie() {} // ✅ Remove o construtor que força UUID
}

