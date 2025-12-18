package com.anime.Site.adapters.config;

import com.anime.Site.adapters.repository.AnimesRepository;
import com.anime.Site.domain.entities.AnimesEntitie;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class AnimeSeedConfig {

    @Bean
    public ApplicationRunner seedAnime(AnimesRepository animesRepository){
        return args -> {
            if (animesRepository.countAll() == 0) {
                AnimesEntitie animesEntitie1 = new AnimesEntitie();
                animesEntitie1.setId(UUID.randomUUID().toString());
                animesEntitie1.setName("Naruto");
                animesEntitie1.setGenero("Ação, Aventura");
                animesEntitie1.setSinopse("Um jovem ninja busca reconhecimento e sonha em se tornar Hokage.");
                animesEntitie1.setDataLancamento("2002-10-03");
                animesEntitie1.setStatus("Completo");
                animesEntitie1.setImagem("https://example.com/naruto.jpg");
                animesEntitie1.setFavorito(true);
                animesEntitie1.getVideos().add("https://example.com/naruto-ep1.mp4");
                // Salvar animesEntitie1 no repositório
                System.out.println("Anime inicial criado: Naruto");
                animesRepository.save(animesEntitie1);
            }
        };
    }
    //Este é o segundo anime de seed, cria um anime adicional se o repositório estiver vazio.
    @Bean
    public ApplicationRunner seedAnime1(AnimesRepository animesRepository){
        return args -> {
            if (animesRepository.countAll() == 1) {
                AnimesEntitie animesEntitie2 = new AnimesEntitie();
                animesEntitie2.setId(UUID.randomUUID().toString());
                animesEntitie2.setName("One Piece");
                animesEntitie2.setGenero("Ação, Aventura, Fantasia");
                animesEntitie2.setSinopse("A jornada de Monkey D. Luffy para encontrar o tesouro lendário One Piece.");
                animesEntitie2.setDataLancamento("1999-10-20");
                animesEntitie2.setStatus("Em andamento");
                animesEntitie2.setImagem("https://example.com/onepiece.jpg");
                animesEntitie2.setFavorito(true);
                animesEntitie2.getVideos().add("https://example.com/onepiece-ep1.mp4");
                // Salvar animesEntitie2 no repositório
                animesRepository.save(animesEntitie2);
                System.out.println("Anime inicial criado: One Piece");
            }
        };
    }
}
