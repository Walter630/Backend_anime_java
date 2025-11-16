package com.anime.Site.adapters.repository;

import com.anime.Site.domain.dto.ChatRequestDTO;

import com.anime.Site.domain.entities.ChatEntitie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<ChatEntitie, String> {
    List<ChatEntitie> findByAnimeIdOrderByDataCriacaoDesc(String animeId);
}

