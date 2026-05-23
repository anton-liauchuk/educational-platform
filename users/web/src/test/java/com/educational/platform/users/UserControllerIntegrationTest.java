package com.educational.platform.users;

import com.educational.platform.users.login.SignInCommandHandler;
import com.educational.platform.users.registration.UserRegistrationCommandHandler;
import com.educational.platform.users.security.UserController;
import com.educational.platform.users.security.WebSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Represents course controller integration tests.
 */
@WebMvcTest(value = UserController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfig.class)})
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserRegistrationCommandHandler userRegistrationCommandHandler;

    @MockitoBean
    private SignInCommandHandler signInCommandHandler;

    @Test
    void signUp_validRequest_signedUp() throws Exception {
        this.mockMvc
                .perform(post("/users/sign-up")
                        .content("""
                                {
                                    "role": "ROLE_STUDENT",
                                    "username": "username",
                                    "email": "mail@gmail.com",
                                    "password": "password"
                                }""")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void signUp_emptyUsername_badRequest() throws Exception {
        this.mockMvc
                .perform(post("/users/sign-up")
                        .content("""
                                {
                                    "role": "ROLE_STUDENT",
                                    "email": "mail@gmail.com",
                                    "password": "password"
                                }""")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUp_emptyEmail_badRequest() throws Exception {
        this.mockMvc
                .perform(post("/users/sign-up")
                        .content(
                                """
                                        {
                                            "role": "ROLE_STUDENT",
                                            "username": "username",
                                            "password": "password"
                                        }""")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUp_emptyPassword_badRequest() throws Exception {
        this.mockMvc
                .perform(post("/users/sign-up")
                        .content("""
                                {
                                    "role": "ROLE_STUDENT",
                                    "username": "username",
                                    "email": "mail@gmail.com"
                                }""")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUp_emptyRole_badRequest() throws Exception {
        this.mockMvc
                .perform(post("/users/sign-up")
                        .content("""
                                {
                                    "username": "username",
                                    "email": "mail@gmail.com",
                                    "password": "password"
                                }""")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signIn_validRequest_signedIn() throws Exception {
        this.mockMvc
                .perform(post("/users/sign-in")
                        .content("""
                                {
                                    "username": "username",
                                    "password": "password"
                                }""")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void signIn_emptyUsername_badRequest() throws Exception {
        this.mockMvc
                .perform(post("/users/sign-in")
                        .content("""
                                {
                                    "password": "password"
                                }""")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signIn_emptyPassword_badRequest() throws Exception {
        this.mockMvc
                .perform(post("/users/sign-in")
                        .content("""
                                {
                                    "username": "username"
                                }""")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
