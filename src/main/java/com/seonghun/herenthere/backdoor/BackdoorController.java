package com.seonghun.herenthere.backdoor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/backdoor")
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public BackdoorController(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("setup-database")
    @Transactional
    public String setupDatabase() {
        deleteAccessTokens();
        deleteUsers();
        createUsers();
        return "clear";
    }

    private void deleteUsers() {
        jdbcTemplate.update("DELETE FROM users");
    }

    private void deleteAccessTokens() {
        jdbcTemplate.update("DELETE FROM access_tokens");
    }

    private void createUsers() {
        LocalDateTime now = LocalDateTime.now();

        jdbcTemplate.update("""
                        INSERT INTO users (
                            id, email, name, password_encoded, role,
                            created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?, ?, ?)
                        """,
                "0BV000USR0001", "tester@example.com", "테스터",
                passwordEncoder.encode("password"), "ROLE_USER",
                now, now
        );

        jdbcTemplate.update("""
                        INSERT INTO users (
                            id, email, name, password_encoded, role,
                            created_at, updated_at)
                        VALUES (?, ?, ?, ?, ?, ?, ?)
                        """,
                "0BV000USR0002", "admin@example.com", "관리자",
                passwordEncoder.encode("password"), "ROLE_ADMIN",
                now, now
        );
    }
}
