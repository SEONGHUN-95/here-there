package com.seonghun.herenthere.controllers;

import com.seonghun.herenthere.application.GetUserService;
import com.seonghun.herenthere.application.SignupService;
import com.seonghun.herenthere.dtos.SignupRequestDto;
import com.seonghun.herenthere.dtos.SignupResultDto;
import com.seonghun.herenthere.dtos.UserDto;
import com.seonghun.herenthere.models.User;
import com.seonghun.herenthere.models.UserId;
import com.seonghun.herenthere.security.AuthUser;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {
    private final SignupService signupService;
    private final GetUserService getUserService;

    public UserController(GetUserService getUserService, SignupService signupService) {
        this.getUserService = getUserService;
        this.signupService = signupService;
    }

    @GetMapping("/me")
    public UserDto me(Authentication authentication) {
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        UserId id = new UserId(authUser.id());
        User user = getUserService.getUser(id);
        return UserDto.of(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SignupResultDto signup(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        String accessToken = signupService.signup(
                signupRequestDto.email().trim(),
                signupRequestDto.name().trim(),
                signupRequestDto.password().trim()
        );
        return new SignupResultDto(accessToken);
    }
}
