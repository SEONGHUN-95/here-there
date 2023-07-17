package com.seonghun.herenthere.security;

public record AuthUser(
        String id,
        String email,
        String password,
        String role,
        String accessToken
) {
    // 로그인 시 사용되는 정보
    public static AuthUser of(
            String id, String email, String password, String role) {
        return new AuthUser(id, email, password, role, "");
    }

    // 로그인 후 token 기반 인가 확인
    public static AuthUser authenticated(
            String id, String role, String accessToken) {
        return new AuthUser(id, "", "", role, accessToken);
    }
}