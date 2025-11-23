package com.anime.Site.adapters.services;

import com.anime.Site.adapters.repository.TokenRepository;
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

    private final TokenRepository tokenRepository;

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Long expirationMinutes; // expiração do ACCESS TOKEN

    @Value("${jwt.refresh}")
    private Long refreshDays; // expiração do REFRESH TOKEN

    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    // ===========================
    // GERAR ACCESS + REFRESH
    // ===========================
    public TokenEntitie gerarTokens(String email, String role) {

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        // ACCESS TOKEN (curto)
        Instant accessExp = LocalDateTime.now()
                .plusMinutes(expirationMinutes)
                .toInstant(ZoneOffset.of("-03:00"));

        String accessToken = JWT.create()
                .withSubject(email)
                .withClaim("role", role)
                .withClaim("type", "access")
                .withExpiresAt(accessExp)
                .sign(algorithm);

        // REFRESH TOKEN (longo)
        Instant refreshExp = LocalDateTime.now()
                .plusDays(refreshDays)
                .toInstant(ZoneOffset.of("-03:00"));

        String refreshToken = JWT.create()
                .withSubject(email)
                .withClaim("type", "refresh")
                .withExpiresAt(refreshExp)
                .sign(algorithm);

        // salva no banco
        TokenEntitie entity = new TokenEntitie();
        entity.setEmail(email);
        entity.setAccessToken(accessToken);
        entity.setRefreshToken(refreshToken);
        entity.setExpiresAt(Date.from(accessExp));
        entity.setExpiresRefresh(Date.from(refreshExp));

        tokenRepository.save(entity);

        return entity;
    }

    // ===========================
    // VALIDAR ACCESS TOKEN
    // ===========================
    public DecodedJWT verificarAccessToken(String token) {

        // verifica se existe no banco
        TokenEntitie t = tokenRepository.findByAccessToken(token);
        if (t == null)
            throw new JWTVerificationException("Token não encontrado.");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        return JWT.require(algorithm)
                .withClaim("type", "access")
                .acceptLeeway(1)
                .build()
                .verify(token);
    }

    // ===========================
    // GERAR NOVO ACCESS PELO REFRESH
    // ===========================
    public String refreshAccessToken(String refreshToken) {

        // valida refresh
        TokenEntitie tokenDb = tokenRepository.findByRefreshToken(refreshToken);

        if (tokenDb == null) {
            throw new JWTVerificationException("Refresh token inválido.");
        }

        if (tokenDb.getExpiresRefresh().before(new Date())) {
            throw new JWTVerificationException("Refresh token expirado. Faça login novamente.");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        String newAccess = JWT.create()
                .withSubject(tokenDb.getEmail())
                .withClaim("type", "access")
                .withExpiresAt(
                        LocalDateTime.now()
                                .plusMinutes(expirationMinutes)
                                .toInstant(ZoneOffset.of("-03:00"))
                )
                .sign(algorithm);

        // atualiza no banco
        tokenDb.setAccessToken(newAccess);
        tokenDb.setExpiresAt(
                Date.from(
                        LocalDateTime.now().plusMinutes(expirationMinutes)
                                .toInstant(ZoneOffset.of("-03:00"))
                )
        );

        tokenRepository.save(tokenDb);

        return newAccess;
    }
}

