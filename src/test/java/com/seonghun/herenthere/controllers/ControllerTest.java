package com.seonghun.herenthere.controllers;

import com.seonghun.herenthere.HerenthereApplication;
import com.seonghun.herenthere.security.AccessTokenGenerator;
import com.seonghun.herenthere.security.AccessTokenService;
import com.seonghun.herenthere.security.AuthUser;
import com.seonghun.herenthere.security.AuthUserDao;
import com.seonghun.herenthere.security.WebSecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ContextConfiguration(classes = {
        HerenthereApplication.class,
        WebSecurityConfig.class,
})
public abstract class ControllerTest {
    protected static final String USER_ID = "UserId";
    protected static final String ADMIN_ID = "AdminId";

    @SpyBean
    private AccessTokenService accessTokenService;

    @SpyBean
    protected AccessTokenGenerator accessTokenGenerator;

    @MockBean
    protected AuthUserDao authUserDao;

    protected String userAccessToken;

    protected String adminAccessToken;

    @BeforeEach
    void setUpAccessTokenAndUserDetailsDaoForAuthentication() {
        userAccessToken = accessTokenGenerator.generate(USER_ID);
        adminAccessToken = accessTokenGenerator.generate(ADMIN_ID);

        AuthUser user = AuthUser.authenticated(
                USER_ID, "ROLE_USER", userAccessToken);

        given(authUserDao.findByAccessToken(userAccessToken))
                .willReturn(Optional.of(user));

        AuthUser admin = AuthUser.authenticated(
                ADMIN_ID, "ROLE_ADMIN", adminAccessToken);

        given(authUserDao.findByAccessToken(adminAccessToken))
                .willReturn(Optional.of(admin));
    }
}