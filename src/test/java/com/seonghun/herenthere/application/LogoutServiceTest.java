package com.seonghun.herenthere.application;

import com.seonghun.herenthere.security.AuthUserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LogoutServiceTest {
    private AuthUserDao authUserDao;
    private LogoutService logoutService;

    @BeforeEach
    void setUp() {
        authUserDao = mock(AuthUserDao.class);
        logoutService = new LogoutService(authUserDao);
    }

    @Test
    void logout() {
        String accessToken = "Tester.Access.Token";

        logoutService.logout(accessToken);

        verify(authUserDao).removeAccessToken(accessToken);
    }
}
