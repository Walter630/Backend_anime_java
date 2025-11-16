package com.anime.Site.adapters.controller;

import com.anime.Site.domain.dto.AdminDTO;
import com.anime.Site.domain.dto.AdminRegistrarDTO;
import com.anime.Site.domain.dto.RegisterDTO;
import com.anime.Site.adapters.services.AuthService;
import com.anime.Site.adapters.services.TokenService;
import com.anime.Site.domain.entities.AdministradorEntitie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AuthService authService;

    @GetMapping("/listar")
    public ResponseEntity<List<AdministradorEntitie>> getAuth() {
        return ResponseEntity.ok(authService.listar());
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AdminDTO body) {
        try{
            // Busca usuário no banco
            AdministradorEntitie admin = authService.login(body);
            var retorno = tokenService.gerarToken(admin.getEmail(), admin.getRole());
            return ResponseEntity.ok(retorno);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/register")
    public ResponseEntity<String> registrar(@RequestBody RegisterDTO body) {
        try{
            authService.registrar(body);
            return ResponseEntity.ok("Usuario registrado com sucesso!");
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register/admin")
    public ResponseEntity<String> registrarAdmin(
            @RequestBody AdminRegistrarDTO adminRegistrarDTO,
            @AuthenticationPrincipal String roleDoUsuario // obtido do token
    ) {
        try {
            authService.registrarAdmin(adminRegistrarDTO, roleDoUsuario);
            return ResponseEntity.ok("Admin registrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

}
