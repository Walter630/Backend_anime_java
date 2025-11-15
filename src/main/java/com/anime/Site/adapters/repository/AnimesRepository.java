package com.anime.Site.adapters.repository;

import com.anime.Site.application.dto.AnimeDto;
import com.anime.Site.domain.entities.AnimesEntitie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimesRepository extends MongoRepository<AnimeDto, String> {
    Optional<AnimeDto> findByNome(String nome);
    List<AnimeDto> findAll();

}
