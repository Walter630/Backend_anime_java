package com.anime.Site.adapters.repository;

import com.anime.Site.domain.entities.AdministradorEntitie;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends MongoRepository<AdministradorEntitie, String> {
    Optional<AdministradorEntitie> findByEmail(String email);

}
