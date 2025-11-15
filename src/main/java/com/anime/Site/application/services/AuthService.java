package com.anime.Site.application.services;

import com.anime.Site.adapters.repository.AdminRepository;
import com.anime.Site.application.dto.AdminDTO;
import com.anime.Site.application.dto.RegisterDTO;
import com.anime.Site.application.dto.AdminRegistrarDTO;
import com.anime.Site.application.dto.RoleDTO;
import com.anime.Site.domain.entities.AdministradorEntitie;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final AdminRepository administradorRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthService(AdminRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public void registrar(RegisterDTO dto) {
        if (administradorRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email já cadastrado!");
        }

        AdministradorEntitie novoAdmin = new AdministradorEntitie();
        novoAdmin.setNome(dto.getNome());
        novoAdmin.setEmail(dto.getEmail());
        novoAdmin.setSenha(passwordEncoder.encode(dto.getSenha()));

        novoAdmin.setRole("USER");
        administradorRepository.save(novoAdmin);
    }

    public AdministradorEntitie login(AdminDTO dto) {
        AdministradorEntitie admin = administradorRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (!passwordEncoder.matches(dto.senha(), admin.getSenha())) {
            throw new RuntimeException("Senha incorreta!");
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

        if (administradorRepository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("Email já cadastrado!");
        }

        AdministradorEntitie novoAdmin = new AdministradorEntitie();
        novoAdmin.setEmail(dto.email());
        novoAdmin.setSenha(passwordEncoder.encode(dto.senha()));
        novoAdmin.setRole("ADMIN"); // sempre ADMIN

        administradorRepository.save(novoAdmin);
    }

}
