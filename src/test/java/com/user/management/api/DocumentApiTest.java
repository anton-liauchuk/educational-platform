package com.user.management.api;

import com.user.management.domain.dto.DocumentDTO;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.get;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

// todo jsonassert dependency from spring boot
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class DocumentApiTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.port = port;
    }

    @Test
    void exampleTest() {
        // todo remove hardcoded url
        final DocumentDTO response = get("/documents?id=1")

                .then()
                .statusCode(HttpStatus.OK.value())
//                .contentType(APPLICATION_JSON_UTF8_VALUE)

                .extract().body().as(DocumentDTO.class);


    }

}
