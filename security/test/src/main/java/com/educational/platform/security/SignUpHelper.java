package com.educational.platform.security;

import io.restassured.http.ContentType;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

/**
 * Represents the component for signing up test user and getting the token. Should be used in API tests.
 */
@Component
public class SignUpHelper {

    /**
     * Signs up the student and returns the token.
     *
     * @return token.
     */
    public String signUpStudent() {
        return given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"role\": \"ROLE_STUDENT\",\n" +
                        "  \"username\": \"username\",\n" +
                        "  \"email\": \"email@gmail.com\",\n" +
                        "  \"password\": \"password\"\n" +
                        "}")

                .when()
                .post("/users/sign-up")
                .asString();
    }

}
