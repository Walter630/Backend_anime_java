package com.anime.Site.adapters.services;

import com.anime.Site.adapters.repository.AnimesRepository;
import com.anime.Site.adapters.repository.UserRepository;
import com.anime.Site.domain.entities.AnimesEntitie;
import com.anime.Site.domain.entities.UserEntitie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AnimesRepository animesRepository;

    public UserService(UserRepository userRepository, AnimesRepository animesRepository) {
        this.userRepository = userRepository;
        this.animesRepository = animesRepository;
    }

    public Optional<UserEntitie> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void toggleFavorito(String userEmail, String animeId) {
        var user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (user.getFavoritos().contains(animeId)) {
            // Já estava favorito → remove
            user.getFavoritos().remove(animeId);
        } else {
            // Não estava → adiciona
            user.getFavoritos().add(animeId);
        }

        userRepository.save(user);
    }

    public List<AnimesEntitie> listarFavoritos(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return animesRepository.findByIds(user.getFavoritos());
    }
}
