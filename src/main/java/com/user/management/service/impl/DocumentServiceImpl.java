package com.user.management.service.impl;

import com.user.management.domain.Document;
import com.user.management.domain.dto.DocumentDTO;
import com.user.management.exception.ResourceNotFoundException;
import com.user.management.repository.DocumentRepository;
import com.user.management.service.DocumentService;
import com.user.management.service.mapper.DocumentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper mapper;

    @Override
    // todo validate that id is null
    public DocumentDTO create(DocumentDTO dto) {
        final Document document = mapper.toDocument(dto);
        final Document saved = documentRepository.save(document);
        return mapper.toDTO(saved);
    }

    @Override
    public void delete(Integer id) {
        try {
            documentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public DocumentDTO find(Integer id) {
        final Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found."));
        return mapper.toDTO(document);
    }

    @Override
    public void update(DocumentDTO dto) {
        final Document document = documentRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Document not found."));
        mapper.updateDocumentFromDTO(dto, document);
    }
}

