package com.anime.Site.adapters.repository;

import com.anime.Site.domain.entities.VideoEntitie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VideosRepository {
    private final JdbcTemplate jdbc;
    public VideosRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    public Optional<VideoEntitie> findByAnimeId(String animeId) {
        List<VideoEntitie> list = jdbc.query(
                "SELECT * FROM videos WHERE anime_id = ?",
                videoMapper,
                animeId
        );
        return list.stream().findFirst();
    }
    public Optional<VideoEntitie> delete(String animeId) {
        jdbc.update("DELETE FROM videos WHERE anime_id = ?", animeId);
        return findByAnimeId(animeId);
    }
    public VideoEntitie save(VideoEntitie video) {
        jdbc.update(
                "INSERT INTO videos (id, anime_id, numero, titulo, video_url) VALUES (?, ?, ?, ?, ?)",
                video.getId(),
                video.getAnimeId(),
                video.getNumero(),
                video.getTitulo(),
                video.getVideoUrl()
        );
        return video;
    }
    public Optional<VideoEntitie> findById(String id) {
        List<VideoEntitie> list = jdbc.query(
                "SELECT * FROM videos WHERE id = ?",
                videoMapper,
                id
        );
        return list.stream().findFirst();
    }
    public List<VideoEntitie> findAll() {
        return jdbc.query("SELECT * FROM videos", videoMapper);
    }
    private final RowMapper<VideoEntitie> videoMapper = (rs, rowNum) -> {
        VideoEntitie video = new VideoEntitie();
        video.setId(rs.getString("id"));
        video.setAnimeId(rs.getString("anime_id"));
        video.setNumero(rs.getInt("numero"));
        video.setTitulo(rs.getString("titulo"));
        video.setVideoUrl(rs.getString("video_url"));
        return video;
    };
}
