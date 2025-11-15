package com.anime.Site.adapters.repository;

import com.anime.Site.domain.entities.MangasEntities;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MangasRepository extends MongoRepository<MangasEntities, String> {
    Optional<MangasEntities> findByNome(String nome);
    List<MangasEntities> findAll();
}
