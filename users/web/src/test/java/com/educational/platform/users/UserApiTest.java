package com.educational.platform.users;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

/**
 * Represents API tests for user functionality.
 */
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
				.log()
				.ifValidationFails(LogDetail.BODY)
				.statusCode(HttpStatus.OK.value());
	}
}
