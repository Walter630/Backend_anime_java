package com.anime.Site.adapters.rabbit.outbound;

import com.anime.Site.adapters.repository.AnimesRepository;
import com.anime.Site.domain.entities.AnimesEntitie;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.ServerResponse;

@Service
public class AnimeCreateProducerService {
    private final AnimesRepository animesRepository;
    private final AnimeCreateProducer animeCreateProducer;

    //Construtor que recebe as dependências
    public AnimeCreateProducerService(AnimesRepository animesRepository, AnimeCreateProducer animeCreateProducer) {
        /*animeCreateProducer.send(StreamMessage.builder()
                .properties(props -> props.applicationProperty("source", "anime-service")
                                            .applicationProperty("eventType", "AnimeCreated"))
                .body(eventJson.getBytes())
        );*/
        this.animesRepository = animesRepository;
        this.animeCreateProducer = animeCreateProducer;
    }

    public AnimesEntitie createAnime(AnimesEntitie anime) {
        AnimesEntitie saved = animesRepository.save(anime);
        animeCreateProducer.send(new AnimeCreateEvent(saved.getId(), saved.getName()));
        return saved;
    }
}
