package com.anime.Site.adapters.repository;

import com.anime.Site.domain.entities.AdministradorEntitie;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AdminRepository {
    private final JdbcTemplate jdbc;

    public AdminRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public List<AdministradorEntitie> findAll() {
        return jdbc.query("SELECT * FROM administradores", adminMapper);
    }
    public Optional<AdministradorEntitie> findById(String id) {
        List<AdministradorEntitie> list = jdbc.query(
                "SELECT * FROM administradores WHERE id = ?",
                adminMapper,
                id
        );
        return list.stream().findFirst();
    }
    public Optional<AdministradorEntitie> findByEmail(String email) {
        List<AdministradorEntitie> list = jdbc.query(
                "SELECT * FROM administradores WHERE email = ?",
                adminMapper,
                email
        );
        return list.stream().findFirst();
    }
    public void save(AdministradorEntitie admin) {
        jdbc.update(
                "INSERT INTO administradores (id, nome, email, senha, role) VALUES (?, ?, ?, ?, ?)",
                admin.getId(),
                admin.getName(),
                admin.getEmail(),
                admin.getPassword(),
                admin.getRole()
        );
    }
    public int count() {
        return jdbc.queryForObject("SELECT COUNT(*) FROM administradores", Integer.class);
    }
    private final RowMapper<AdministradorEntitie> adminMapper = (rs, rowNum) -> {
        AdministradorEntitie a = new AdministradorEntitie();
        a.setId(rs.getString("id"));
        a.setName(rs.getString("nome"));
        a.setEmail(rs.getString("email"));
        a.setPassword(rs.getString("senha"));
        a.setRole(rs.getString("role"));
        return a;
    };
}