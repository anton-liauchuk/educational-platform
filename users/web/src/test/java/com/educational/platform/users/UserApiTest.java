package com.educational.platform.users;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import static io.restassured.RestAssured.given;

/**
 * Represents API tests for user functionality.
 */
@TestPropertySource("classpath:application-security.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void signUp_validRequest_signedUp() {
        given()
                .contentType(ContentType.JSON)
                .body("{\n" + "    \"role\": \"ROLE_STUDENT\",\n" + "    \"username\": \"username\",\n" + "    \"email\": \"mail@gmail.com\",\n" + "    \"password\": \"password\"\n" + "}")

                .when()
                .post("/users/sign-up")

                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void signIn_validRequest_signedIn() {
        given()
                .contentType(ContentType.JSON)
                .body("{\n" + "    \"role\": \"ROLE_STUDENT\",\n" + "    \"username\": \"username\",\n" + "    \"email\": \"mail@gmail.com\",\n" + "    \"password\": \"password\"\n" + "}")

                .when()
                .post("/users/sign-up");

        given()
                .contentType(ContentType.JSON)
                .body("{\n" + "    \"username\": \"username\",\n" + "    \"password\": \"password\"\n" + "}")

                .when()
                .post("/users/sign-in")

                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
