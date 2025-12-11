package com.anime.Site.adapters.services;

import com.anime.Site.adapters.repository.AdminRepository;
import com.anime.Site.domain.entities.AdministradorEntitie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    public List<AdministradorEntitie> listar() {
      return adminRepository.findAll();
    }
    public Optional<AdministradorEntitie> buscarPorId(String id) {
      return adminRepository.findById(id);
    }
    public Optional<AdministradorEntitie> buscarPorEmail(String email) {
      return adminRepository.findByEmail(email);
    }
    public void salvar(AdministradorEntitie admin) {
      adminRepository.save(admin);
    }
}
