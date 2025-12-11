package com.anime.Site.adapters.services;

import com.anime.Site.adapters.repository.AnimesRepository;
import com.anime.Site.domain.entities.AnimesEntitie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimesService {
    @Autowired
    private AnimesRepository animesRepository;

    public List<AnimesEntitie> findAll() throws Exception {
        return animesRepository.findAll();
    }

    public Optional<AnimesEntitie> findByNome(String nome) {
        return animesRepository.findByNome(nome);
    }

    public void delete(String animesEntitie) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new RuntimeException("Usuário sem permissão para deletar anime!");
        }
        animesRepository.delete(animesEntitie);
    }

    public AnimesEntitie save(AnimesEntitie animeDto) {

        // Pega a role do usuário logado
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new RuntimeException("Usuário sem permissão para cadastrar anime!");
        }

        if (animesRepository.findByNome(animeDto.getNome()).isPresent()) {
            throw new RuntimeException("Anime já cadastrado!");
        }

        return animesRepository.save(animeDto);

    }

    public void findById(String id) {
        animesRepository.findById(id);
    }

    public void addVideo(String animeId, String videoUrl) {
        var anime = animesRepository.findById(animeId)
                .orElseThrow(() -> new RuntimeException("Anime não encontrado"));

        anime.getVideos().add(videoUrl);
        animesRepository.save(anime);
    }
}
