package com.seonghun.herenthere.application;

import com.seonghun.herenthere.models.User;
import com.seonghun.herenthere.models.UserId;
import com.seonghun.herenthere.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class GetUserService {
    private final UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(UserId userId) {
        return userRepository.findById(userId).orElseThrow();
    }
}
