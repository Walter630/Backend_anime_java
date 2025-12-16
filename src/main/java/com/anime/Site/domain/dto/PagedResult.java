package com.anime.Site.domain.dto;

import java.util.List;

public record PagedResult<T>(
        List<T> content,
        int page,
        int size,
        int totalElements,
        int totalPages
) {
    public PagedResult {
        totalPages = (int) Math.ceil((double) totalElements / size);
    }
}

