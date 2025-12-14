package com.anime.Site.adapters.repository;

import com.anime.Site.domain.entities.MangasEntities;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MangasRepository {
    private final JdbcTemplate jdbc;

    public MangasRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public List<MangasEntities> findAll() {
        return jdbc.query("SELECT * FROM mangas", mangasMapper);
    }
    public Optional<MangasEntities> findById(String id) {
        List<MangasEntities> list = jdbc.query(
                "SELECT * FROM mangas WHERE id = ?",
                mangasMapper,
                id
        );
        return list.stream().findFirst();
    }
    public Optional<MangasEntities> findByNome(String nome) {
        List<MangasEntities> list = jdbc.query(
                "SELECT * FROM mangas WHERE nome = ?",
                mangasMapper,
                nome
        );
        return list.stream().findFirst();
    }
    public MangasEntities save(MangasEntities manga) {
        jdbc.update(
                "INSERT INTO mangas (nome, genero, sinopse, data_lancamento, status, imagem) VALUES (?, ?, ?, ?, ?, ?)",

                manga.getNome(),
                manga.getGenero(),
                manga.getSinopse(),
                manga.getDataLancamento(),
                manga.getStatus(),
                manga.getImagem()
        );
        return manga;
    }
    public Optional<MangasEntities> delete(String id) {
        jdbc.update("DELETE FROM mangas WHERE id = ?", id);
        return findById(id);
    }
    private final RowMapper<MangasEntities> mangasMapper = (rs, rowNum) -> {
        MangasEntities manga = new MangasEntities();
        manga.setId(rs.getString("id"));
        manga.setNome(rs.getString("nome"));
        manga.setGenero(rs.getString("genero"));
        manga.setSinopse(rs.getString("sinopse"));
        manga.setDataLancamento(rs.getString("data_lancamento"));
        manga.setStatus(rs.getString("status"));
        manga.setImagem(rs.getString("imagem"));
        return manga;
    };
}