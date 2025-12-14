package com.anime.Site.adapters.controller;

import com.anime.Site.adapters.services.AuthService;
import com.anime.Site.adapters.services.TokenService;
import com.anime.Site.domain.dto.AdminDTO;
import com.anime.Site.domain.dto.AdminRegistrarDTO;
import com.anime.Site.domain.dto.RegisterDTO;
import com.anime.Site.domain.entities.AdministradorEntitie;
import com.anime.Site.domain.entities.TokenEntitie;
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

    @GetMapping("/findAll")
    public ResponseEntity<List<AdministradorEntitie>> getAuth() {
        return ResponseEntity.ok(authService.listar());
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminDTO body) {
        try {
            AdministradorEntitie admin = authService.login(body);
            TokenEntitie tokens = tokenService.gerarTokens(admin.getEmail(), admin.getRole());
            return ResponseEntity.ok(tokens);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
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

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(
            @RequestHeader("Authorization") String authHeader
    ) {
        try {
            String refreshToken = authHeader.replace("Bearer ", "");
            String newAccessToken = tokenService.refreshAccessToken(refreshToken);
            return ResponseEntity.ok(newAccessToken);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/verify-token")
    public ResponseEntity<?> verifyToken(@RequestHeader("Authorization") String token) {
        try {
            authService.verificarToken(token);
            return ResponseEntity.ok("Token válido!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
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
