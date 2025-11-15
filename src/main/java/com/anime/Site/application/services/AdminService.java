package com.anime.Site.application.services;

import com.anime.Site.adapters.repository.AdminRepository;
import com.anime.Site.domain.entities.AdministradorEntitie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    public List<AdministradorEntitie> listar() {
      return adminRepository.findAll();
    }
}
