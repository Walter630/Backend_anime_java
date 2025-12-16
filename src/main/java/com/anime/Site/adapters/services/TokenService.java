package com.anime.Site.adapters.services;

import com.anime.Site.domain.entities.TokenEntitie;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expirationMinutes;

    @Value("${jwt.refresh}")
    private Long refreshDays;

    // ===========================
    // GERAR ACCESS + REFRESH (SEM BANCO)
    // ===========================
    public TokenEntitie gerarTokens(String email, String role) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        // ACCESS
        Instant accessExp = LocalDateTime.now()
                .plusMinutes(expirationMinutes)
                .toInstant(ZoneOffset.of("-03:00"));

        String accessToken = JWT.create()
                .withSubject(email)
                .withClaim("role", role)
                .withClaim("type", "access")
                .withExpiresAt(accessExp)
                .sign(algorithm);

        // REFRESH
        Instant refreshExp = LocalDateTime.now()
                .plusDays(refreshDays)
                .toInstant(ZoneOffset.of("-03:00"));

        String refreshToken = JWT.create()
                .withSubject(email)
                .withClaim("type", "refresh")
                .withExpiresAt(refreshExp)
                .sign(algorithm);

        TokenEntitie entity = new TokenEntitie();
        entity.setEmail(email);
        entity.setAccessToken(accessToken);
        entity.setRefreshToken(refreshToken);
        entity.setExpiresAt(Date.from(accessExp));
        entity.setExpiresRefresh(Date.from(refreshExp));

        return entity;
    }

    // ===========================
    // VALIDAR ACCESS TOKEN (SEM BANCO)
    // ===========================
    public DecodedJWT verificarAccessToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            System.out.println("depois do algoritmo"+algorithm);
            return JWT.require(algorithm)
                    .withClaim("type", "access")
                    .acceptLeeway(1)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Token inválido: " + e.getMessage());
        }
    }

    // ===========================
    // VALIDAR REFRESH TOKEN (SEM BANCO)
    // ===========================
    public boolean isValidateRefreshToken(String refreshToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWT.require(algorithm)
                    .withClaim("type", "refresh")
                    .acceptLeeway(1)
                    .build()
                    .verify(refreshToken);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    // ===========================
    // REFRESH ACCESS (SEM BANCO - só gera novo)
    // ===========================
    public String refreshAccessToken(String refreshToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            DecodedJWT decoded = JWT.require(algorithm)
                    .withClaim("type", "refresh")
                    .build()
                    .verify(refreshToken);

            String email = decoded.getSubject();

            // Gera novo access token
            Instant accessExp = LocalDateTime.now()
                    .plusMinutes(expirationMinutes)
                    .toInstant(ZoneOffset.of("-03:00"));

            return JWT.create()
                    .withSubject(email)
                    .withClaim("type", "access")
                    .withExpiresAt(accessExp)
                    .sign(algorithm);

        } catch (JWTVerificationException e) {
            throw new JWTVerificationException("Refresh token inválido: " + e.getMessage());
        }
    }
}
