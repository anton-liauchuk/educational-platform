package com.user.management.documents.service.impl;

import com.user.management.api.exception.ResourceNotFoundException;
import com.user.management.documents.domain.Document;
import com.user.management.documents.domain.dto.DocumentDTO;
import com.user.management.documents.domain.DocumentRepository;
import com.user.management.documents.service.DocumentService;
import com.user.management.documents.service.mapper.DocumentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

// todo review is it needed to have tests for methods without logic
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
        input.setName("test_name" );
        final Document savedDocument = new Document();
        savedDocument.setId(111);
        savedDocument.setName("test_name" );
        doReturn(savedDocument).when(documentRepository).save(argThat((Document d) -> d.getName().equals(input.getName())));

        final DocumentDTO result = documentService.create(input);

        verify(documentRepository).save(argThat((Document d) -> d.getName().equals(input.getName())));
        verify(documentMapper).toDocument(input);
        verify(documentMapper).toDTO(argThat((Document d) -> d.getName().equals(input.getName())));
        assertThat(result)
                .hasFieldOrPropertyWithValue("id", savedDocument.getId())
                .hasFieldOrPropertyWithValue("name", savedDocument.getName());
    }

    @Test
    void createNotValidDocumentMustThrowConstraintViolationExceptionTest() {
        final DocumentDTO input = new DocumentDTO();

        assertThrows(ConstraintViolationException.class,
                () -> documentService.create(input));
    }

    @Test
    void createNullDocumentMustThrowConstraintViolationExceptionTest() {
        assertThrows(ConstraintViolationException.class,
                () -> documentService.create(null));
    }

    @Test
    void deleteValidDocumentMustDeleteDocumentTest() {
        documentService.delete(1);

        verify(documentRepository).deleteById(1);
    }

    @Test
    void deleteNotExistingDocumentMustThrowResourceNotFoundExceptionTest() {
        doThrow(new EmptyResultDataAccessException(1)).when(documentRepository).deleteById(234);

        assertThrows(ResourceNotFoundException.class,
                () -> documentService.delete(234));
    }

    @Test
    void findExistingDocumentMustReturnDocumentTest() {
        final Document existingDocument = new Document();
        existingDocument.setId(111);
        existingDocument.setName("test_name" );
        doReturn(Optional.of(existingDocument)).when(documentRepository).findById(111);

        final DocumentDTO result = documentService.find(111);

        verify(documentRepository).findById(111);
        assertThat(result)
                .hasFieldOrPropertyWithValue("id", 111)
                .hasFieldOrPropertyWithValue("name", "test_name" );
    }

    @Test
    void findNotExistingDocumentMustThrowResourceNotFoundExceptionTest() {
        doReturn(Optional.empty()).when(documentRepository).findById(111);

        assertThrows(ResourceNotFoundException.class,
                () -> documentService.find(111));
    }

    @Test
    void updateExistingDocumentMustUpdateDocumentTest() {
        final DocumentDTO input = new DocumentDTO();
        input.setId(111);
        input.setName("test_name" );
        final Document existingDocument = new Document();
        existingDocument.setId(111);
        existingDocument.setName("test_name" );
        doReturn(Optional.of(existingDocument)).when(documentRepository).findById(111);

        documentService.update(input);

        verify(documentRepository).findById(111);
        verify(documentMapper).updateDocumentFromDTO(input, existingDocument);
    }

    @Test
    void updateNotExistingDocumentMustThrowResourceNotFoundExceptionTest() {
        final DocumentDTO input = new DocumentDTO();
        input.setId(111);
        input.setName("test_name" );
        doReturn(Optional.empty()).when(documentRepository).findById(111);

        assertThrows(ResourceNotFoundException.class,
                () -> documentService.update(input));
    }

    @Test
    void updateNotValidDocumentMustThrowConstraintViolationExceptionTest() {
        final DocumentDTO input = new DocumentDTO();

        assertThrows(ConstraintViolationException.class,
                () -> documentService.update(input));
    }

    @Test
    void updateNullDocumentMustThrowConstraintViolationExceptionTest() {
        assertThrows(ConstraintViolationException.class,
                () -> documentService.update(null));
    }
}
