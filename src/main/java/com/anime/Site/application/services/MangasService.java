package com.anime.Site.application.services;

import com.anime.Site.adapters.repository.MangasRepository;
import com.anime.Site.domain.entities.MangasEntities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MangasService {
    @Autowired
    private MangasRepository mangasRepository;

    public List<MangasEntities> findAll() {
        return mangasRepository.findAll();
    }
    public Optional<MangasEntities> findByNome(String nome) {
        return mangasRepository.findByNome(nome);
    }
    public MangasEntities save(MangasEntities mangasEntities) {
        return mangasRepository.save(mangasEntities);
    }
    public void delete(MangasEntities mangasEntities) {
        mangasRepository.delete(mangasEntities);
    }
}
