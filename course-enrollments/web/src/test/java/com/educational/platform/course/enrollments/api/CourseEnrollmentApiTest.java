package com.educational.platform.course.enrollments.api;

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
 * Represents API tests for administration of course proposal functionality.
 */
@Sql(scripts = "classpath:insert_data.sql")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CourseEnrollmentApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void register_validCourse_createdWithUUID() {
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"student\": \"username\"\n" +
                        "}")

                .when()
                .post("/courses/{uuid}/course-enrollments", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))

                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

}
