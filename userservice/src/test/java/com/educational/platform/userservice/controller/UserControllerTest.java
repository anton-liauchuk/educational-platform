package com.educational.platform.userservice.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @LocalServerPort
    int port;


    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }


    @Test
    void register() {
        with().body("{\n" +
                "  \"username\": \"test_username\",\n" +
                "  \"email\": \"test_email@gmail.com\",\n" +
                "  \"password\": \"test_password\",\n" +
                "  \"roles\": [\n" +
                "    \"ROLE_CLIENT\"\n" +
                "  ]\n" +
                "}")

                .when()
                .post("/signup")

                .then()
                .statusCode(200);
    }
}
