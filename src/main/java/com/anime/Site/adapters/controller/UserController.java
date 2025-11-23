package com.anime.Site.adapters.controller;

import com.anime.Site.adapters.services.UserService;
import com.anime.Site.domain.entities.AnimesEntitie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/favoritar/{animeId}")
    public ResponseEntity<Void> toggleFavorito(@PathVariable String animeId, @AuthenticationPrincipal String emailDoUser) {
        userService.toggleFavorito(animeId, emailDoUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/favoritos")
    public ResponseEntity<List<AnimesEntitie>> listarFavoritos(@AuthenticationPrincipal String emailDoUser) {
        return ResponseEntity.ok(userService.listarFavoritos(emailDoUser));
    }
}
