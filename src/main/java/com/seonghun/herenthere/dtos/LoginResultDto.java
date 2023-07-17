package com.seonghun.herenthere.dtos;

public record LoginResultDto(
        String accessToken,
        String role
) {
}
