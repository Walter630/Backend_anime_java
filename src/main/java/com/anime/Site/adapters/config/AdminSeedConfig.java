package com.anime.Site.adapters.config;

import com.anime.Site.adapters.repository.AdminRepository;
import com.anime.Site.domain.entities.AdministradorEntitie;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AdminSeedConfig {

    @Bean
    public ApplicationRunner seedAdmin(AdminRepository adminRepository) {
        return args -> {
            if (adminRepository.count() == 0) { // banco vazio
                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                AdministradorEntitie admin = new AdministradorEntitie();
                admin.setEmail("admin@site.com");          // Defina o email
                admin.setSenha(encoder.encode("123456"));  // Defina a senha inicial
                admin.setRole("ADMIN");                    // Role ADMIN
                adminRepository.save(admin);
                System.out.println("Admin inicial criado: admin@site.com / 123456");
            }
        };
    }
}
