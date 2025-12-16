package com.anime.Site.adapters.controller;

import com.anime.Site.adapters.services.AuthService;
import com.anime.Site.adapters.services.TokenService;
import com.anime.Site.domain.dto.AdminDTO;
import com.anime.Site.domain.dto.AdminRegistrarDTO;
import com.anime.Site.domain.dto.RegisterDTO;
import com.anime.Site.domain.entities.AdministradorEntitie;
import com.anime.Site.domain.entities.TokenEntitie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    public ResponseEntity<?> login(@RequestBody AdminDTO body, HttpServletResponse response) {
        try {
            AdministradorEntitie admin = authService.login(body);
            TokenEntitie tokens = tokenService.gerarTokens(admin.getEmail(), admin.getRole());

            // Define o cookie de refresh token
            jakarta.servlet.http.Cookie refreshTokenCookie = new jakarta.servlet.http.Cookie("refreshToken", tokens.getRefreshToken());
            refreshTokenCookie.setHttpOnly(true);
            refreshTokenCookie.setSecure(false);
            refreshTokenCookie.setPath("/"); // Disponível para todo o domínio
            refreshTokenCookie.setMaxAge((int) TimeUnit.DAYS.toSeconds(7)); // 7 dias
            response.addCookie(refreshTokenCookie);
            return ResponseEntity.ok(Map.of("accessToken", tokens.getAccessToken()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registrar(@RequestBody RegisterDTO body) {
        try {
            authService.registrar(body);
            return ResponseEntity.ok("Usuario registrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(
            HttpServletRequest request
    ) {
        try {
            String refreshToken = getCookieValue(request, "refreshToken");
            if (refreshToken == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token não encontrado");
            }


            String newAccessToken = tokenService.refreshAccessToken(refreshToken);
            return ResponseEntity.ok(Map.of("accessToken", newAccessToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    private String getCookieValue(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (jakarta.servlet.http.Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
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

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        // Invalida o cookie de refresh token
        jakarta.servlet.http.Cookie refreshTokenCookie = new jakarta.servlet.http.Cookie("refreshToken", null);
        refreshTokenCookie.setHttpOnly(true); // Não acessível via JavaScript ele serve para não ser acessível via JavaScript
        //refreshTokenCookie.setSecure(true);// Defina como true em produção se estiver usando HTTPS
        refreshTokenCookie.setPath("/"); // Disponível para todo o domínio // Ele serve para todo o domínio
        refreshTokenCookie.setMaxAge(0); // Expira imediatamente // Ele serve para expirar imediatamente e define o tempo de vida do cookie como 0
        response.addCookie(refreshTokenCookie); // Adiciona o cookie à resposta
        return ResponseEntity.ok("Logout realizado com sucesso!");
    }
}