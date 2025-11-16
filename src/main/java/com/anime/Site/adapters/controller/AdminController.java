package com.anime.Site.adapters.controller;

import com.anime.Site.adapters.services.AdminService;
import com.anime.Site.domain.entities.AdministradorEntitie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @GetMapping
    public List<AdministradorEntitie> getAdmin() {
        return adminService.listar();
    }

    @PostMapping
    public String postAdmin() {
        return "Admin";
    }
}
