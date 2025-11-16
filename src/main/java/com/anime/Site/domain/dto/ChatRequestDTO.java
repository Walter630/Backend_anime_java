package com.anime.Site.domain.dto;

import java.time.Instant;

public record ChatRequestDTO(String mensagem, String usuarioId, String animeId, Instant dataCriacao) {
}
