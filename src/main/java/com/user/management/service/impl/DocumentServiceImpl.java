package com.user.management.service.impl;

import com.user.management.domain.Document;
import com.user.management.domain.dto.DocumentDTO;
import com.user.management.exception.ResourceNotFoundException;
import com.user.management.mapper.DocumentMapper;
import com.user.management.repository.DocumentRepository;
import com.user.management.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentMapper documentMapper;


    @Override
    // todo validate that id is null
    public DocumentDTO create(DocumentDTO document) {
        return documentMapper.toDTO(documentRepository.save(documentMapper.toDocument(document)));
    }

    @Override
    public void deleteById(Integer id) {
        try {
            documentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    @Override
    public DocumentDTO findById(Integer id) {
        final Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found."));
        return documentMapper.toDTO(document);
    }

    @Override
    public void update(Integer id, DocumentDTO dto) {
        final Document document = documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found."));
        documentMapper.updateDocumentFromDTO(dto, document);
    }
}

