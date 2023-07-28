package com.seonghun.herenthere.dtos;

import jakarta.validation.constraints.NotBlank;

public record PostCreateDto(
        @NotBlank
        String title,

        @NotBlank
        String content
) {
}
