package com.anime.Site.adapters.services;

import com.anime.Site.adapters.repository.AnimesRepository;
import com.anime.Site.domain.dto.AnimeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimesService {
    @Autowired
    private AnimesRepository animesRepository;

    public List<AnimeDto> findAll() {
        return animesRepository.findAll();
    }

    public Optional<AnimeDto> findByNome(String nome) {
        return animesRepository.findByNome(nome);
    }

    public void delete(AnimeDto animeDto) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new RuntimeException("Usuário sem permissão para deletar anime!");
        }
        animesRepository.delete(animeDto);
    }

    public AnimeDto save(AnimeDto animeDto) {

        // Pega a role do usuário logado
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            throw new RuntimeException("Usuário sem permissão para cadastrar anime!");
        }

        if (animesRepository.findByNome(animeDto.nome()).isPresent()) {
            throw new RuntimeException("Anime já cadastrado!");
        }

        return animesRepository.save(animeDto);

    }

    public void findById(String id) {
        animesRepository.findById(id);
    }
}
