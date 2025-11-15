package com.anime.Site.application.services;

import com.anime.Site.adapters.repository.TokenRepository;
import com.anime.Site.domain.entities.TokenEntitie;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    private final TokenRepository tokenRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expirationMinutes;  // AGORA realmente minutos

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String gerarToken(String email, String role) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant expiracao = getExpiracao();

        String token = JWT.create()
                .withSubject(email)
                .withClaim("role", role)  // <<--- agora dinâmico (ADMIN / USER)
                .withExpiresAt(expiracao)
                .sign(algorithm);

        // SALVAR TOKEN NO BANCO
        TokenEntitie tokenEntitie = new TokenEntitie();
        tokenEntitie.setToken(token);
        tokenRepository.save(tokenEntitie);

        return token;
    }

    public DecodedJWT verificarToken(String token) throws JWTVerificationException {

        // GARANTE QUE O TOKEN EXISTE
        if(!tokenRepository.existsByToken(token)){
            throw new JWTVerificationException("Token não encontrado. Faça login novamente.");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .acceptLeeway(1) // tolerância
                .build();

        return verifier.verify(token);
    }

    private Instant getExpiracao() {
        // expiration é em MINUTOS agora
        return LocalDateTime.now()
                .plusMinutes(expirationMinutes)
                .toInstant(ZoneOffset.of("-03:00"));
    }
}
