package com.anime.Site.adapters.services;

import com.anime.Site.adapters.repository.VideosRepository;
import com.anime.Site.domain.entities.VideoEntitie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    @Autowired
    private VideosRepository videosRepository;

    public List<VideoEntitie> findAll() {
        return videosRepository.findAll();
    }

    public Optional<VideoEntitie> findByAnimeId(String animeId) {
        return videosRepository.findByAnimeId(animeId);
    }

    public VideoEntitie save(VideoEntitie videoEntitie) {
        return videosRepository.save(videoEntitie);
    }

    public VideoEntitie delete(VideoEntitie videoEntitie) {
        videosRepository.delete(videoEntitie);
        return videoEntitie;
    }
}
