package com.seonghun.herenthere.controllers;

import com.seonghun.herenthere.application.GetUserService;
import com.seonghun.herenthere.application.SignupService;
import com.seonghun.herenthere.exceptions.EmailAlreadyTaken;
import com.seonghun.herenthere.models.User;
import com.seonghun.herenthere.models.UserId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static com.seonghun.herenthere.security.Role.REGULAR_USER;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest extends ControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserService getUserService;

    @MockBean
    private SignupService signupService;

    @Test
    @DisplayName("GET /users/me")
    void detail() throws Exception {
        UserId userId = new UserId(USER_ID);

        User user = new User(userId, "tester@example.com", "jack", REGULAR_USER, "Password", new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return null;
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return false;
            }
        });


        given(getUserService.getUser(userId)).willReturn(user);

        mockMvc.perform(get("/users/me")
                        .header("Authorization", "Bearer " + userAccessToken))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("name")));
    }

    @Test
    @DisplayName("POST /users - with valid email, name and password")
    void signupSuccess() throws Exception {
        String email = "newbie@example.com";
        String name = "Newbie";
        String password = "password";

        String json = String.format(
                """
                        {
                            "email": "%s",
                            "name": "%s",
                            "password": "%s"
                        }
                        """,
                email, name, password
        );

        given(signupService.signup(email, name, password))
                .willReturn("NEWBIE.ACCESS.TOKEN");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("accessToken")));
    }

    @Test
    @DisplayName("POST /users - with invalid email")
    void signupFail() throws Exception {
        String email = "newbie";
        String name = "Newbie";
        String password = "password";

        String json = String.format(
                """
                        {
                            "email": "%s",
                            "name": "%s",
                            "password": "%s"
                        }
                        """,
                email, name, password
        );

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /users - when email has already been taken")
    void signupEmailAlreadyTaken() throws Exception {
        String email = "tester@example.com";
        String name = "Tester";
        String password = "password";

        String json = String.format(
                """
                        {
                            "email": "%s ",
                            "name": "%s",
                            "password": "%s"
                        }
                        """,
                email, name, password
        );

        given(signupService.signup(email, name, password))
                .willThrow(new EmailAlreadyTaken(email));

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest());
    }
}