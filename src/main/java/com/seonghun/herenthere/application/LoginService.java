package com.seonghun.herenthere.application;

import com.seonghun.herenthere.dtos.LoginResultDto;
import com.seonghun.herenthere.security.AccessTokenGenerator;
import com.seonghun.herenthere.security.AuthUserDao;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {
    private final AuthUserDao authUserDao;

    private final PasswordEncoder passwordEncoder;

    private final AccessTokenGenerator accessTokenGenerator;

    public LoginService(AuthUserDao authUserDao, PasswordEncoder passwordEncoder, AccessTokenGenerator accessTokenGenerator) {
        this.authUserDao = authUserDao;
        this.passwordEncoder = passwordEncoder;
        this.accessTokenGenerator = accessTokenGenerator;
    }

    public LoginResultDto login(String email, String password) {
        return authUserDao.findByEmail(email)
                .filter(authUser ->
                        passwordEncoder.matches(password, authUser.password()))
                .map(authUser -> {
                    String id = authUser.id();
                    String role = authUser.role();
                    String accessToken = accessTokenGenerator.generate(id);
                    authUserDao.addAccessToken(id, accessToken);
                    return new LoginResultDto(accessToken, role);
                })
                .orElseThrow(() -> new BadCredentialsException("Login Failed"));
    }
}