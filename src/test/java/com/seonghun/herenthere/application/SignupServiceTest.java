package com.seonghun.herenthere.application;


import com.seonghun.herenthere.exceptions.EmailAlreadyTaken;
import com.seonghun.herenthere.repositories.UserRepository;
import com.seonghun.herenthere.security.AccessTokenGenerator;
import com.seonghun.herenthere.security.AuthUserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SignupServiceTest {
    private AuthUserDao authUserDao;

    private PasswordEncoder passwordEncoder;

    private AccessTokenGenerator accessTokenGenerator;

    private SignupService signupService;

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        authUserDao = mock(AuthUserDao.class);
        accessTokenGenerator = new AccessTokenGenerator("SECRET");
        passwordEncoder = mock(PasswordEncoder.class);
        userRepository = mock(UserRepository.class);
        signupService = new SignupService(userRepository, authUserDao, accessTokenGenerator, passwordEncoder);
    }

    @Test
    @DisplayName("signup successful")
    void signupSuccess() {
        String email = "test@example.com";
        String name = "Tester";
        String password = "password";
        String encodedPassword = "ENCODED-PASSWORD";
        given(userRepository.existsByEmail(email)).willReturn(false);
        given(passwordEncoder.encode(password)).willReturn(encodedPassword);
        String accessToken = signupService.signup(email, name, password);
        assertThat(accessToken).isNotBlank();
        verify(authUserDao).addAccessToken(any(), eq(accessToken));
    }

    @Test
    @DisplayName("signup - when email has already been taken")
    void signupEmailAlreadyTaken() {
        String email = "tester@example.com";
        String name = "Tester";
        String password = "password";

        given(userRepository.existsByEmail(email)).willReturn(true);

        assertThatThrownBy(() -> {
            signupService.signup(email, name, password);
        }).isInstanceOf(EmailAlreadyTaken.class);

    }
}