package com.user.management.documents.service.impl;

import com.user.management.api.exception.ResourceNotFoundException;
import com.user.management.core.validator.ThrowableValidator;
import com.user.management.documents.domain.DocumentRepository;
import com.user.management.documents.domain.dto.DocumentDTO;
import com.user.management.documents.service.DocumentService;
import com.user.management.documents.service.mapper.DocumentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;
    private final DocumentMapper mapper;
    private final ThrowableValidator validator;

    @Override
    // todo validate that id is null
    public DocumentDTO create(DocumentDTO dto) {
        validator.validate(dto);
        var input = mapper.toDocument(dto);
        var saved = repository.save(input);
        return mapper.toDTO(saved);
    }

    @Override
    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public DocumentDTO find(Integer id) {
        var document = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found."));
        return mapper.toDTO(document);
    }

    @Override
    public void update(DocumentDTO dto) {
        validator.validate(dto);
        var document = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Document not found."));
        mapper.updateDocumentFromDTO(dto, document);
    }
}

