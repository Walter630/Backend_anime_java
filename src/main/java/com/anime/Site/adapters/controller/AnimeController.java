package com.anime.Site.adapters.controller;

import com.anime.Site.adapters.services.AnimesService;
import com.anime.Site.domain.dto.PagedResult;
import com.anime.Site.domain.entities.AnimesEntitie;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> cadastrarAnime(@Valid @RequestBody AnimesEntitie animeDto) {
        try {
            AnimesEntitie salvo = animesService.save(animeDto);
            System.out.println("Anime salvo: " + salvo);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
