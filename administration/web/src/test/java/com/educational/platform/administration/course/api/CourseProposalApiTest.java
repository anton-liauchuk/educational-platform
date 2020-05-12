package com.educational.platform.administration.course.api;

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
public class CourseProposalApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void approve_existingCourseProposal_noContent() {
        given()
                .contentType(ContentType.JSON)

                .when()
                .put("/administration/course-proposals/{uuid}/approval-status", UUID.fromString("123e4567-e89b-12d3-a456-426655440001"))

                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void decline_existingCourseProposal_noContent() {
        given()
                .contentType(ContentType.JSON)

                .when()
                .delete("/administration/course-proposals/{uuid}/approval-status", UUID.fromString("123e4567-e89b-12d3-a456-000000000000"))

                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

}
