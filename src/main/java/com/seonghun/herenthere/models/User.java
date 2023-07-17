package com.seonghun.herenthere.models;

import com.seonghun.herenthere.security.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @EmbeddedId
    private UserId id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password_encoded")
    private String encodedPassword;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private User() {
    }

    public User(UserId id, String email, String name, Role role, String password, PasswordEncoder passwordEncoder) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.encodedPassword = changePassword(password, passwordEncoder);
    }

    public UserId id() {
        return id;
    }

    public String name() {
        return name;
    }

    private String changePassword(String password, PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(password);
    }


}
