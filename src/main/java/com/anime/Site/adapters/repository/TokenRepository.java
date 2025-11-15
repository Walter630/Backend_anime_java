package com.anime.Site.adapters.repository;

import com.anime.Site.domain.entities.TokenEntitie;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository  extends MongoRepository<TokenEntitie, String> {
    boolean existsByToken(String token);
}
