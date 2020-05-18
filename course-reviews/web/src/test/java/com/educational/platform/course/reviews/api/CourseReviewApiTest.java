package com.educational.platform.course.reviews.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.util.UUID;

import static io.restassured.RestAssured.given;

/**
 * Represents API tests for course reviews functionality.
 */
@Sql(scripts = "classpath:insert_data.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseReviewApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void review_validRequest_created() {
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"reviewer\": \"username\",\n" +
                        "  \"rating\": 3.2\n" +
                        "}")

                .when()
                .post("/courses/{uuid}/course-reviews", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))

                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    void update_validRequest_ok() {
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"reviewer\": \"username\",\n" +
                        "  \"rating\": 3.2\n" +
                        "}")

                .when()
                .post("/courses/{uuid}/course-reviews", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))

                .then()
                .statusCode(HttpStatus.OK.value());
    }

}
