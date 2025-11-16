package com.anime.Site.domain.dto;

public enum RoleDTO {
    ADMIN("ADMIN"),
    USER("USER");

    private final String role;

    RoleDTO(String role) {
        this.role = role;
    }

/* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
    /**
     * Retorna o valor da role.
     *
     * @return o valor da role
     */
/* <<<<<<<<<<  0ab47832-e428-4127-8b85-48b3a2884fff  >>>>>>>>>>> */
    public String getRole() {
        return role;
    }

}
