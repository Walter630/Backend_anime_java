package com.anime.Site.adapters.repository;

import com.anime.Site.domain.entities.UserEntitie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntitie, String> {

    Optional<UserEntitie> findByEmail(String email);

    Optional<UserEntitie> findById(String id);

    List<UserEntitie> findAll();
}
