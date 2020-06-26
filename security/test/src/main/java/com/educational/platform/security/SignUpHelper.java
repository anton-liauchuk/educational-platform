package com.educational.platform.security;

import io.restassured.http.ContentType;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

/**
 * Represents the component for signing up test user and getting the token. Should be used in API tests.
 */
public class SignUpHelper {

    /**
     * Signs up the teacher and returns the token.
     *
     * @return token.
     */
    public static String signUpTeacher() {
        var response = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"role\": \"ROLE_TEACHER\",\n" +
                        "  \"username\": \"username\",\n" +
                        "  \"email\": \"email@gmail.com\",\n" +
                        "  \"password\": \"password\"\n" +
                        "}")

                .when()
                .post("/users/sign-up")
                .then()
                .extract()
                .response();

        if (response.statusCode() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
            return signIn();
        }

        return response.body().asString();
    }

    /**
     * Signs up the student and returns the token.
     *
     * @return token.
     */
    public static String signUpStudent() {
        var response = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"role\": \"ROLE_STUDENT\",\n" +
                        "  \"username\": \"username\",\n" +
                        "  \"email\": \"email@gmail.com\",\n" +
                        "  \"password\": \"password\"\n" +
                        "}")

                .when()
                .post("/users/sign-up")
                .then()
                .extract()
                .response();

        if (response.statusCode() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
            return signIn();
        }

        return response.body().asString();
    }

    /**
     * Signs in the student and returns the token.
     *
     * @return token.
     */
    private static String signIn() {
        return given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"username\": \"username\",\n" +
                        "  \"password\": \"password\"\n" +
                        "}")

                .when()
                .post("/users/sign-in")
                .asString();
    }

}
