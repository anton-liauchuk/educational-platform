package com.educational.platform.users.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.with;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiTest {

    @LocalServerPort
    int port;


    @BeforeEach
    void setUp() {
        RestAssured.port = port;
    }


    @Test
    void register_validUserRegistration_ok() throws FileNotFoundException {
        with().body(ResourceUtils.getFile(this.getClass().getResource("/valid_signup.json")))
                .contentType(ContentType.JSON)

                .when()
                .post("/users/sign-up")

                .then()
                .statusCode(HttpStatus.OK.value())
                .contentType(ContentType.JSON);
    }

    @Test
    void register_invalidPassword_badRequest() throws FileNotFoundException {
        with().body(ResourceUtils.getFile(this.getClass().getResource("/invalid_password_signup.json")))
                .contentType(ContentType.JSON)

                .when()
                .post("/users/sign-up")

                .then()
                .log().all()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .contentType(ContentType.JSON);
    }
}
