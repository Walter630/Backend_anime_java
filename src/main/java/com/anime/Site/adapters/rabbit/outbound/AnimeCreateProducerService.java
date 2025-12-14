package com.anime.Site.adapters.rabbit.outbound;

import com.anime.Site.adapters.repository.AnimesRepository;
import com.anime.Site.domain.entities.AnimesEntitie;
import org.springframework.stereotype.Service;

@Service
public class AnimeCreateProducerService {
    private final AnimesRepository animesRepository;
    private final AnimeCreateProducer animeCreateProducer;

    //Construtor que recebe as dependências
    public AnimeCreateProducerService(AnimesRepository animesRepository, AnimeCreateProducer animeCreateProducer) {
        this.animesRepository = animesRepository;
        this.animeCreateProducer = animeCreateProducer;
    }

    public AnimesEntitie createAnime(AnimesEntitie anime) {
        AnimesEntitie saved = animesRepository.save(anime);
        animeCreateProducer.send(new AnimeCreateEvent(saved.getId(), saved.getName()));
        return saved;
    }
}
