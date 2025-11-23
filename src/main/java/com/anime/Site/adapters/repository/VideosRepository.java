package com.anime.Site.adapters.repository;

import com.anime.Site.domain.entities.VideoEntitie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideosRepository extends MongoRepository<VideoEntitie, String> {
    Optional<VideoEntitie> findByAnimeId(String animeId);
}
