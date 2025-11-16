package com.anime.Site.adapters.controller;

import com.anime.Site.domain.dto.AnimeDto;
import com.anime.Site.adapters.services.AnimesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anime")
public class AnimeController {
    @Autowired
    private AnimesService animesService;

    @PostMapping("/cadastrar")
    public ResponseEntity<AnimeDto> cadastrarAnime(@RequestBody AnimeDto animeDto) {
        AnimeDto salvo = animesService.save(animeDto);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AnimeDto>> getAnimes() {
        return ResponseEntity.ok(animesService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<String> getAnime(@PathVariable String id) {
        return ResponseEntity.ok(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnime(AnimeDto animeDto) {
        animesService.delete(animeDto);
        return ResponseEntity.ok().build();
    }
}
