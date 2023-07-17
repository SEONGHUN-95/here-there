package com.seonghun.herenthere.application;

import com.seonghun.herenthere.exceptions.EmailAlreadyTaken;
import com.seonghun.herenthere.models.User;
import com.seonghun.herenthere.models.UserId;
import com.seonghun.herenthere.repositories.UserRepository;
import com.seonghun.herenthere.security.AccessTokenGenerator;
import com.seonghun.herenthere.security.AuthUserDao;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.seonghun.herenthere.security.Role.REGULAR_USER;

@Service
@Transactional
public class SignupService {
    private final UserRepository userRepository;
    private final AuthUserDao authUserDao;
    private final AccessTokenGenerator accessTokenGenerator;
    private final PasswordEncoder passwordEncoder;

    public SignupService(UserRepository userRepository, AuthUserDao authUserDao, AccessTokenGenerator accessTokenGenerator, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.authUserDao = authUserDao;
        this.accessTokenGenerator = accessTokenGenerator;
        this.passwordEncoder = passwordEncoder;
    }

    public String signup(String email, String name, String password) {
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyTaken(email);
        }
        UserId userId = createUser(email, name, password);
        return createAccessToken(userId);
    }

    private String createAccessToken(UserId userId) {
        String accessToken = accessTokenGenerator.generate(userId.toString());
        authUserDao.addAccessToken(userId.toString(), accessToken);
        return accessToken;
    }

    private UserId createUser(String email, String name, String password) {
        UserId userId = UserId.generate();
        User user = new User(userId, email, name, REGULAR_USER, password, passwordEncoder);
        userRepository.save(user);

        return userId;
    }
}
