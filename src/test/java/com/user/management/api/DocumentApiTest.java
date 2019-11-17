package com.user.management.api;

import com.user.management.domain.dto.DocumentDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

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
    void createGivenValidDocumentMustReturnCorrespondingDocumentTest() throws FileNotFoundException {
        final Integer id = createDocument();
        // todo remove hardcoded url
        final DocumentDTO result = get("/documents/{id}", id)

                .then()
                .statusCode(HttpStatus.OK.value())

                .extract()
                .as(DocumentDTO.class);

        assertThat(result)
                .hasFieldOrPropertyWithValue("name", "test_document_name");
    }

    private Integer createDocument() throws FileNotFoundException {
        final DocumentDTO response = given()
                .contentType(ContentType.JSON)

                .when()
                .body((ResourceUtils.getFile(this.getClass().getResource("/document_request.json"))))
                .post("/documents")

                .then()
                .statusCode(HttpStatus.CREATED.value())
                .contentType(ContentType.JSON)
                .extract()
                .as(DocumentDTO.class);
        return response.getId();
    }

}
