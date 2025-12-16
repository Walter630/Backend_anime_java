package com.anime.Site.adapters.controller;

import com.anime.Site.adapters.services.AnimesService;
import com.anime.Site.domain.dto.PagedResult;
import com.anime.Site.domain.entities.AnimesEntitie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/anime")
public class AnimeController {
    @Autowired
    private AnimesService animesService;

    @PostMapping("/register")
    public ResponseEntity<AnimesEntitie> cadastrarAnime(@RequestBody AnimesEntitie animeDto) {
        try {
            AnimesEntitie salvo = animesService.save(animeDto);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AnimesEntitie>> getAnimes() throws Exception {
        return ResponseEntity.ok(animesService.findAll());
    }

    @GetMapping("/getAllPage")
    public ResponseEntity<PagedResult<AnimesEntitie>> getAnimesPage(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam String sortBy
    ) {
        var result = animesService.findAllPage(page, size, sortBy);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getAnime(@PathVariable String id) {
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AnimesEntitie> deleteAnime(String animeId) {
        animesService.delete(animeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getByName")
    public ResponseEntity<Optional<AnimesEntitie>> buscarAnimeNome(@RequestParam String nome) {
        return ResponseEntity.ok(animesService.findByNome(nome));
    }

    @PostMapping("/anime/{id}/addPlay")
    public ResponseEntity<String> addVideo(
            @PathVariable String id,
            @RequestBody Map<String, String> body
    ) {
        String url = body.get("url");
        animesService.addVideo(id, url);
        return ResponseEntity.ok("Vídeo adicionado com sucesso!");
    }
}
