package com.user.management.api;

import com.user.management.domain.dto.TagDTO;
import com.user.management.domain.dto.TagDTO;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.documentationConfiguration;

@ExtendWith(RestDocumentationExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class TagApiTest {

    private RequestSpecification documentationSpec;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup(RestDocumentationContextProvider restDocumentation) {
        RestAssured.port = port;
        documentationSpec = new RequestSpecBuilder()
                .addFilter(documentationConfiguration(restDocumentation)).build();
    }

    @Test
    void findByNameGivenExistingTagNameMustReturnCorrespondingTagTest() throws FileNotFoundException {
        final String name = createTag();
        final TagDTO result = given(documentationSpec)
                .filter(document("findByName",
                        responseFields(
                                fieldWithPath("name").description("Tag name"))))
                .get("/tags/{name}", name)

                .then()
                .statusCode(HttpStatus.OK.value())

                .extract()
                .as(TagDTO.class);

        assertThat(result).hasFieldOrPropertyWithValue("name", "test_tag_name");
    }

    @Test
    void findByNameGivenNotExistingTagNameMustReturnNotFoundTest() {
        get("/tags/{name}", "not_existing_tag_name")

                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

//    @Test
//    void findByNameGivenInvalidTagNameMustReturnBadRequestTest() {
//        get("/tags/{name}", "invalid_id_32")
//
//                .then()
//                .statusCode(HttpStatus.BAD_REQUEST.value());
//    }

    @Test
    void createValidTagMustReturnCreatedTagTest() throws FileNotFoundException {
        final TagDTO response = given(documentationSpec)
                .filter(document("create",
                        requestFields(
                                fieldWithPath("name").description("Tag name")
                        ),
                        responseFields(
                                fieldWithPath("name").description("Tag name"))))
                .contentType(ContentType.JSON)

                .when()
                .body((ResourceUtils.getFile(this.getClass().getResource("/create_tag_request.json"))))
                .post("/tags")

                .then()
                .statusCode(HttpStatus.CREATED.value())
                .contentType(ContentType.JSON)
                .extract()
                .as(TagDTO.class);

        assertThat(response).hasFieldOrPropertyWithValue("name", "test_tag_name");
    }

//    @Test
//    void createInvalidTagMustReturnBadRequestTest() throws FileNotFoundException {
//        given()
//                .contentType(ContentType.JSON)
//
//                .when()
//                .body((ResourceUtils.getFile(this.getClass().getResource("/invalid_document_request.json"))))
//                .post("/tags")
//
//                .then()
//                .statusCode(HttpStatus.BAD_REQUEST.value());
//    }

    @Test
    void deleteGivenExistingTagNameMustReturnNoContentTest() throws FileNotFoundException {
        final String name = createTag();
        delete("/tags/{name}", name)

                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }

    @Test
    void deleteGivenNotExistingTagNameMustReturnBadRequestTest() {
        given(documentationSpec)
                .filter(document("delete"))

                .when()
                .delete("/tags/{name}", 12345)

                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

//    @Test
//    void updateGivenExistingTagNameMustReturnOkTest() throws FileNotFoundException {
//        final String name = createTag();
//        given(documentationSpec)
//                .filter(document("update",
//                        requestFields(
//                                fieldWithPath("name").description("Tag name")
//                        )))
//                .contentType(ContentType.JSON)
//
//                .when()
//                .body((ResourceUtils.getFile(this.getClass().getResource("/update_tag_request.json"))))
//                .put("/tags/{name}", name)
//
//                .then()
//                .statusCode(HttpStatus.OK.value());
//
//        final TagDTO updatedTag = get("/tags/{name}", name)
//
//                .then()
//                .statusCode(HttpStatus.OK.value())
//
//                .extract()
//                .as(TagDTO.class);
//
//        assertThat(updatedTag)
//                .hasFieldOrPropertyWithValue("name", "update_test_tag_name");
//    }

//    @Test
//    void updateGivenNotExistingTagNameMustReturnNotFoundTest() throws FileNotFoundException {
//        given()
//                .contentType(ContentType.JSON)
//
//                .when()
//                .body((ResourceUtils.getFile(this.getClass().getResource("/update_tag_request.json"))))
//                .put("/tags/{name}", 12345)
//
//                .then()
//                .statusCode(HttpStatus.NOT_FOUND.value());
//    }

//    @Test
//    void updateGivenInValidTagNameMustReturnNotFoundTest() throws FileNotFoundException {
//        given()
//                .contentType(ContentType.JSON)
//
//                .when()
//                .body((ResourceUtils.getFile(this.getClass().getResource("/update_document_request.json"))))
//                .put("/tags/{name}", "invalid_id_32")
//
//                .then()
//                .statusCode(HttpStatus.BAD_REQUEST.value());
//    }

//    @Test
//    void updateGivenInValidTagMustReturnNotFoundTest() throws FileNotFoundException {
//        final String name = createTag();
//
//        given()
//                .contentType(ContentType.JSON)
//
//                .when()
//                .body((ResourceUtils.getFile(this.getClass().getResource("/invalid_document_request.json"))))
//                .put("/tags/{name}", name)
//
//                .then()
//                .statusCode(HttpStatus.BAD_REQUEST.value());
//    }

    private String createTag() throws FileNotFoundException {
        final TagDTO response = given()
                .contentType(ContentType.JSON)

                .when()
                .body((ResourceUtils.getFile(this.getClass().getResource("/create_tag_request.json"))))
                .post("/tags")

                .then()
                .statusCode(HttpStatus.CREATED.value())
                .contentType(ContentType.JSON)
                .extract()
                .as(TagDTO.class);
        return response.getName();
    }

}
