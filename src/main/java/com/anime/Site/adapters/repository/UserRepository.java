package com.anime.Site.adapters.repository;

import com.anime.Site.domain.entities.UserEntitie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class UserRepository {

    private final JdbcTemplate jdbc;

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<UserEntitie> findAll() {
        return jdbc.query("SELECT * FROM users", userMapper);
    }

    public Optional<UserEntitie> findById(String id) {
        List<UserEntitie> list = jdbc.query(
                "SELECT * FROM users WHERE id = ?",
                userMapper,
                id
        );
        return list.stream().findFirst();
    }

    public Optional<UserEntitie> findByEmail(String email) {
        List<UserEntitie> list = jdbc.query(
                "SELECT * FROM users WHERE email = ?",
                userMapper,
                email
        );
        return list.stream().findFirst();
    }

    public void save(UserEntitie user) {
        jdbc.update(
                "INSERT INTO users (id, name, email, password) VALUES (?, ?, ?, ?)",
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    private final RowMapper<UserEntitie> userMapper = (rs, rowNum) -> {
        UserEntitie u = new UserEntitie();
        u.setId(rs.getString("id"));
        u.setName(rs.getString("name"));
        u.setEmail(rs.getString("email"));
        u.setPassword(rs.getString("password"));
        return u;
    };
}

