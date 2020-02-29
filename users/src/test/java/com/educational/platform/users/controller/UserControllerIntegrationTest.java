package com.educational.platform.users.controller;

import com.educational.platform.common.exception.UnprocessableEntityException;
import com.educational.platform.users.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.ResourceUtils;

import java.nio.file.Files;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
@ActiveProfiles("test")
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    public void register_usernameInUse_badRequest() throws Exception {
        when(userService.signUp(any())).thenThrow(new UnprocessableEntityException("Username: [test_username] is already in use"));

        mockMvc.perform(post("/users/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile(this.getClass().getResource("/valid_signup.json")).toPath())))
                .andExpect(status().isUnprocessableEntity());
    }


    @Test
    public void register_validSignUp_ok() throws Exception {
        when(userService.signUp(any())).thenReturn("TOKEN-VALUE");

        mockMvc.perform(post("/users/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(Files.readAllBytes(ResourceUtils.getFile(this.getClass().getResource("/valid_signup.json")).toPath())))
                .andExpect(status().isOk());
    }

}
