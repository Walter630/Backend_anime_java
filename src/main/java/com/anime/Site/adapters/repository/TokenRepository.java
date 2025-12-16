package com.anime.Site.adapters.repository;

import com.anime.Site.domain.entities.TokenEntitie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRepository {
    private final JdbcTemplate jdbc;

    private final RowMapper<TokenEntitie> tokenMapper = (rs, rowNum) -> {
        TokenEntitie t = new TokenEntitie();
        t.setAccessToken(rs.getString("access_token"));
        t.setRefreshToken(rs.getString("refresh_token"));
        t.setEmail(rs.getString("email"));
        t.setExpiresAt(rs.getTimestamp("expires_at"));
        t.setExpiresRefresh(rs.getTimestamp("expires_refresh"));
        return t;
    };
    public TokenRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public boolean existsByAccessToken(String accessToken) {
        return jdbc.queryForObject("SELECT COUNT(*) FROM tokens WHERE access_token = ?", Integer.class, accessToken) > 0;
    }

    public TokenEntitie findByEmail(String email) {
        return jdbc.queryForObject("SELECT access_token FROM tokens WHERE email = ?",tokenMapper, email);
    }

    public boolean existsByRefreshToken(String refreshToken) {
        return jdbc.queryForObject("SELECT COUNT(*) FROM tokens WHERE refresh_token = ?", Integer.class, refreshToken) > 0;
    }

    public TokenEntitie findByRefreshToken(String refreshToken) {
        return jdbc.queryForObject("SELECT * FROM tokens WHERE refresh_token = ?", tokenMapper, refreshToken);
    }

    public TokenEntitie findByAccessToken(String accessToken) {
        return jdbc.queryForObject("SELECT * FROM tokens WHERE access_token = ?", tokenMapper, accessToken);
    }

    public TokenEntitie save(TokenEntitie token) {
        jdbc.update("INSERT INTO tokens (access_token, refresh_token, email, expires_at, expires_refresh) VALUES (?, ?, ?, ?, ?)",
                token.getAccessToken(), token.getRefreshToken(), token.getEmail(), token.getExpiresAt(), token.getExpiresRefresh());
        return token;
    }




}
