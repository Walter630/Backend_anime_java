package com.anime.Site.domain.entities;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Document(collection = "Administrador")
@Getter
@Setter
@AllArgsConstructor
public class AdministradorEntitie {
    @Id
    private String id;

    private String nome;
    private String email;
    private String senha;
    private String role;

    public AdministradorEntitie() {
    }

}
