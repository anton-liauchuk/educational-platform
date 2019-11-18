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

import static io.restassured.RestAssured.*;
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
    void findByIdGivenExistingDocumentIdMustReturnCorrespondingDocumentTest() throws FileNotFoundException {
        final Integer id = createDocument();
        // todo remove hardcoded url
        final DocumentDTO result = get("/documents/{id}", id)

                .then()
                .statusCode(HttpStatus.OK.value())

                .extract()
                .as(DocumentDTO.class);

        assertThat(result).hasFieldOrPropertyWithValue("name", "test_document_name");
    }

    @Test
    void findByIdGivenNotExistingDocumentIdMustReturnNotFoundTest() {
        // todo recheck that this document id does not exist
        get("/documents/{id}", 12345)

                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void findByIdGivenInvalidDocumentIdMustReturnBadRequestTest() {
        get("/documents/{id}", "invalid_id_32")

                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void createValidDocumentMustReturnCreatedDocumentTest() throws FileNotFoundException {
        final DocumentDTO response = given()
                .contentType(ContentType.JSON)

                .when()
                .body((ResourceUtils.getFile(this.getClass().getResource("/create_document_request.json"))))
                .post("/documents")

                .then()
                .statusCode(HttpStatus.CREATED.value())
                .contentType(ContentType.JSON)
                .extract()
                .as(DocumentDTO.class);

        assertThat(response).hasFieldOrPropertyWithValue("name", "test_document_name");
        assertThat(response.getId()).isNotNull();
    }

    @Test
    void createInvalidDocumentMustReturnBadRequestTest() throws FileNotFoundException {
        given()
                .contentType(ContentType.JSON)

                .when()
                .body((ResourceUtils.getFile(this.getClass().getResource("/invalid_document_request.json"))))
                .post("/documents")

                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void deleteGivenExistingDocumentIdMustReturnNoContentTest() throws FileNotFoundException {
        final Integer id = createDocument();
        delete("/documents/{id}", id)

                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void deleteGivenNotExistingDocumentIdMustReturnBadRequestTest() {
        // todo recheck that this document id does not exist
        delete("/documents/{id}", 12345)

                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void updateGivenExistingDocumentIdMustReturnOkTest() throws FileNotFoundException {
        final Integer id = createDocument();
        // todo review given method
        given()
                .contentType(ContentType.JSON)

                .when()
                .body((ResourceUtils.getFile(this.getClass().getResource("/update_document_request.json"))))
                .put("/documents/{id}", id)

                .then()
                .statusCode(HttpStatus.OK.value());

        final DocumentDTO updatedDocument = get("/documents/{id}", id)

                .then()
                .statusCode(HttpStatus.OK.value())

                .extract()
                .as(DocumentDTO.class);

        assertThat(updatedDocument)
                .hasFieldOrPropertyWithValue("id", id)
                .hasFieldOrPropertyWithValue("name", "update_test_document_name");
    }

    @Test
    void updateGivenNotExistingDocumentIdMustReturnNotFoundTest() throws FileNotFoundException {
        given()
                .contentType(ContentType.JSON)

                .when()
                .body((ResourceUtils.getFile(this.getClass().getResource("/update_document_request.json"))))
                .put("/documents/{id}", 12345)

                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

    @Test
    void updateGivenInValidDocumentIdMustReturnNotFoundTest() throws FileNotFoundException {
        given()
                .contentType(ContentType.JSON)

                .when()
                .body((ResourceUtils.getFile(this.getClass().getResource("/update_document_request.json"))))
                .put("/documents/{id}", "invalid_id_32")

                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void updateGivenInValidDocumentMustReturnNotFoundTest() throws FileNotFoundException {
        final Integer id = createDocument();

        given()
                .contentType(ContentType.JSON)

                .when()
                .body((ResourceUtils.getFile(this.getClass().getResource("/invalid_document_request.json"))))
                .put("/documents/{id}", id)

                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    // todo review throwing exception
    private Integer createDocument() throws FileNotFoundException {
        final DocumentDTO response = given()
                .contentType(ContentType.JSON)

                .when()
                .body((ResourceUtils.getFile(this.getClass().getResource("/create_document_request.json"))))
                .post("/documents")

                .then()
                .statusCode(HttpStatus.CREATED.value())
                .contentType(ContentType.JSON)
                .extract()
                .as(DocumentDTO.class);
        return response.getId();
    }

}
