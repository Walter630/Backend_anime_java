package com.anime.Site.adapters.repository;

import com.anime.Site.domain.entities.AnimesEntitie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AnimesRepository {
    private final JdbcTemplate jdbc;
    private final RowMapper<AnimesEntitie> animesMapper = (rs, rowNum) -> {
        AnimesEntitie anime = new AnimesEntitie();
        anime.setId(rs.getString("id"));
        anime.setName(rs.getString("nome"));
        anime.setGenero(rs.getString("genero"));
        anime.setSinopse(rs.getString("sinopse"));
        anime.setDataLancamento(rs.getString("data_lancamento"));
        anime.setStatus(rs.getString("status"));
        anime.setImagem(rs.getString("imagem"));
        return anime;
    };
// Buscar todos os animes paginados e ordenados pelo campo passado por parâmetro
    public List<AnimesEntitie> findAllPage(int page, int size, String sortBy) {
        int offset = page * size;
        String sql = String.format("SELECT * FROM animes ORDER BY %s LIMIT %d OFFSET %d",
                sortBy, size, offset) ;
        return jdbc.query(sql, animesMapper);
    }
    // Contar todos os animes na tabela
    public int countAll() {
        return jdbc.queryForObject("SELECT COUNT(*) FROM animes",
                Integer.class
        );
    }
    // Buscar animes com o nome parecido com o passado por parâmetro, paginando os resultados
    public List<AnimesEntitie> findByNamePage(String name, int page, int size) {
        int offset = page * size;
        String sql = String.format("SELECT * FROM animes WHERE nome LIKE ? LIMIT %d OFFSET %d",
                size, offset);
        return jdbc.query(sql, animesMapper, "%" + name + "%");
    }
    // Contar quantos animes existem com o nome parecido com o passado por parâmetro
    public int countByName(String name) {
        return jdbc.queryForObject(
                "SELECT COUNT(*) FROM animes WHERE name LIKE ?",
                Integer.class,
                "%" + name + "%"
        );
    }
    public AnimesRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public List<AnimesEntitie> findAll() {
        return jdbc.query("SELECT * FROM animes", animesMapper);
    }

    public Optional<AnimesEntitie> findById(String id) {
        List<AnimesEntitie> list = jdbc.query(
                "SELECT * FROM animes WHERE id = ?",
                animesMapper,
                id
        );
        return list.stream().findFirst();
    }

    public Optional<AnimesEntitie> findByNome(String nome) {
        List<AnimesEntitie> list = jdbc.query(
                "SELECT * FROM animes WHERE name = ?",
                animesMapper,
                nome
        );
        return list.stream().findFirst();
    }

    public Optional<AnimesEntitie> findByFavorito(Boolean favorito) {
        List<AnimesEntitie> list = jdbc.query(
                "SELECT * FROM animes WHERE favorito = ?",
                animesMapper,
                favorito
        );
        return list.stream().findFirst();
    }

    public List<AnimesEntitie> findByIds(List<String> ids) {
        if (ids.isEmpty()) {
            return List.of();
        }
        String placeholders = String.join(",", ids.stream().map(id -> "?").toList());
        return jdbc.query(
                "SELECT * FROM animes WHERE id IN (" + placeholders + ")",
                animesMapper,
                ids.toArray()
        );
    }

    public Optional<AnimesEntitie> delete(String id) {
        jdbc.update("DELETE FROM animes WHERE id = ?", id);
        return findById(id);
    }

    public AnimesEntitie save(AnimesEntitie anime) {
        jdbc.update(
                "INSERT INTO animes (id, name, genero, sinopse, data_lancamento, favorito, status, imagem, videos) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                anime.getId(),
                anime.getName(),
                anime.getGenero(),
                anime.getSinopse(),
                anime.getDataLancamento(),
                anime.getStatus(),
                anime.getImagem(),
                anime.getFavorito(),
                anime.getVideos()
        );
        return anime;
    }
}
