package com.anime.Site.adapters.repository;

import com.anime.Site.domain.entities.AnimesEntitie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimesRepository extends MongoRepository<AnimesEntitie, String> {
    Optional<AnimesEntitie> findByNome(String nome);

    List<AnimesEntitie> findAll();

    List<AnimesEntitie> findByFavorito(List<String> favorito);

}
