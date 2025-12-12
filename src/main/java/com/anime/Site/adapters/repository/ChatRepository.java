package com.anime.Site.adapters.repository.ChatRepository;

import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.util.List;
import com.anime.Site.domain.entities.ChatEntitie;

@Repository
public class ChatRepository {

    private final JdbcTemplate jdbc;

    public ChatRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private final RowMapper<ChatEntitie> chatMapper = (rs, rowNum) ->
            ChatEntitie.builder()
                    .id(rs.getString("id"))
                    .mensagem(rs.getString("mensagem"))
                    .usuarioId(rs.getString("usuario_id"))
                    .animeId(rs.getString("anime_id"))
                    .dataCriacao(rs.getTimestamp("data_criacao").toInstant())
                    .dataEdicao(rs.getTimestamp("data_edicao") != null ? rs.getTimestamp("data_edicao").toInstant() : null)
                    .like(rs.getInt("likes"))             // renomeie sua coluna
                    .dislike(rs.getInt("dislikes"))       // renomeie sua coluna
                    .ativo(rs.getBoolean("ativo"))
                    .build();

    public List<ChatEntitie> findAll() {
        return jdbc.query("SELECT * FROM chat", chatMapper);
    }

    public ChatEntitie save(ChatEntitie chat) {
        jdbc.update("""
            INSERT INTO chat 
              (id, mensagem, usuario_id, anime_id, data_criacao, data_edicao, likes, dislikes, ativo)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
        """,
                chat.getId(),
                chat.getMensagem(),
                chat.getUsuarioId(),
                chat.getAnimeId(),
                chat.getDataCriacao(),
                chat.getDataEdicao(),
                chat.getLike(),
                chat.getDislike(),
                chat.getAtivo()
        );

        return chat;
    }
}
