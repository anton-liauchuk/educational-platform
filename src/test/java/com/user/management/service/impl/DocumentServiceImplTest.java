package com.user.management.service.impl;

import com.user.management.domain.Document;
import com.user.management.domain.dto.DocumentDTO;
import com.user.management.mapper.DocumentMapper;
import com.user.management.repository.DocumentRepository;
import com.user.management.service.DocumentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@SpringBootTest
class DocumentServiceImplTest {

    @MockBean
    private DocumentRepository documentRepository;

    @SpyBean
    private DocumentMapper documentMapper;

    @Autowired
    private DocumentService documentService;

    @Test
    void createValidDocumentMustReturnDocumentTest() {
        final DocumentDTO input = new DocumentDTO();
        input.setName("test_name");
        final Document expectedResult = new Document();
        expectedResult.setId(111);
        expectedResult.setName("test_name");
        // todo remove any
        doReturn(expectedResult).when(documentRepository).save(any());

        final DocumentDTO result = documentService.create(input);

        verify(documentRepository).save(argThat((Document d) -> d.getName().equals(input.getName())));
        verify(documentMapper).toDocument(input);
        verify(documentMapper).toDTO(argThat((Document d) -> d.getName().equals(input.getName())));
        assertThat(result)
                .hasFieldOrPropertyWithValue("id", expectedResult.getId())
                .hasFieldOrPropertyWithValue("name", expectedResult.getName());
    }
}
