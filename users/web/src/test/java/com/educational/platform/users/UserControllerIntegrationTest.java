package com.educational.platform.users;

import com.educational.platform.users.registration.UserRegistrationCommandHandler;
import com.educational.platform.users.security.UserController;
import com.educational.platform.users.security.WebSecurityConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Represents course controller integration tests.
 */
@WebMvcTest(value = UserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class}, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = WebSecurityConfig.class)})
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRegistrationCommandHandler registrationCommandHandler;

    @Test
    void signUp_validRequest_signedUp() throws Exception {
        this.mockMvc
                .perform(post("/users/sign-up")
                        .content("{\n" + "    \"role\": \"ROLE_STUDENT\",\n" + "    \"username\": \"username\",\n"
                                + "    \"email\": \"mail@gmail.com\",\n" + "    \"password\": \"password\"\n" + "}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void signUp_emptyUsername_badRequest() throws Exception {
        this.mockMvc
                .perform(post("/users/sign-up")
                        .content("{\n" + "    \"role\": \"ROLE_STUDENT\",\n" + "    \"email\": \"mail@gmail.com\",\n"
                                + "    \"password\": \"password\"\n" + "}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUp_emptyEmail_badRequest() throws Exception {
        this.mockMvc
                .perform(post("/users/sign-up")
                        .content(
                                "{\n" + "    \"role\": \"ROLE_STUDENT\",\n" + "    \"username\": \"username\",\n" + "    \"password\": \"password\"\n"
                                        + "}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUp_emptyPassword_badRequest() throws Exception {
        this.mockMvc
                .perform(post("/users/sign-up")
                        .content("{\n" + "    \"role\": \"ROLE_STUDENT\",\n" + "    \"username\": \"username\",\n"
                                + "    \"email\": \"mail@gmail.com\"\n" + "}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void signUp_emptyRole_badRequest() throws Exception {
        this.mockMvc
                .perform(post("/users/sign-up")
                        .content("{\n" + "    \"username\": \"username\",\n" + "    \"email\": \"mail@gmail.com\",\n"
                                + "    \"password\": \"password\"\n" + "}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
