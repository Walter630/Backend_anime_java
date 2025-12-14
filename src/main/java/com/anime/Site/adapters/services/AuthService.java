package com.anime.Site.adapters.services;

import com.anime.Site.adapters.repository.AdminRepository;
import com.anime.Site.domain.dto.AdminDTO;
import com.anime.Site.domain.dto.AdminRegistrarDTO;
import com.anime.Site.domain.dto.RegisterDTO;
import com.anime.Site.domain.entities.AdministradorEntitie;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    private final AdminRepository administradorRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private TokenService tokenService;

    public AuthService(AdminRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public void registrar(RegisterDTO dto) {
        if (administradorRepository.findByEmail(dto.getEmail()) != null) {
            throw new RuntimeException("Email já cadastrado!");
        }

        AdministradorEntitie novoAdmin = new AdministradorEntitie();
        novoAdmin.setId(UUID.randomUUID().toString());
        novoAdmin.setName(dto.getName());
        novoAdmin.setEmail(dto.getEmail());
        novoAdmin.setPassword(passwordEncoder.encode(dto.getPassword()));
        novoAdmin.setIsActive(true);

        novoAdmin.setRole("USER");
        administradorRepository.save(novoAdmin);
    }

        public AdministradorEntitie login(AdminDTO body) {

            AdministradorEntitie admin =
                    administradorRepository.findByEmail(body.email());

            if (admin == null) {
                throw new RuntimeException("Usuário não encontrado");
            }

            if (!passwordEncoder.matches(body.password(), admin.getPassword())) {
                throw new RuntimeException("Senha incorreta");
            }

            if (!admin.getIsActive()) {
                throw new RuntimeException("Usuário inativo");
            }

            return admin;
        }

    public List<AdministradorEntitie> listar(){
        return administradorRepository.findAll();
    }

    public void registrarAdmin(AdminRegistrarDTO dto, String roleDoSolicitante) {
        // verifica se quem está criando é ADMIN
        if (!"ADMIN".equals(roleDoSolicitante)) {
            throw new RuntimeException("Apenas admins podem criar outros admins!");
        }

        if (administradorRepository.findByEmail(dto.email()) != null) {
            throw new RuntimeException("Email já cadastrado!");
        }

        AdministradorEntitie novoAdmin = new AdministradorEntitie();
        novoAdmin.setEmail(dto.email());
        novoAdmin.setPassword(passwordEncoder.encode(dto.senha()));
        novoAdmin.setIsActive(true);
        novoAdmin.setRole("ADMIN"); // sempre ADMIN

        administradorRepository.save(novoAdmin);
    }

    public DecodedJWT verificarToken(String token) {
        try {
            return tokenService.verificarAccessToken(token);
        } catch (Exception e) {
            throw new RuntimeException("Token inválido!");
        }
    }
}
