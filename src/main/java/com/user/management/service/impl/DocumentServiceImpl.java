package com.user.management.service.impl;

import com.user.management.exception.ResourceNotFoundException;
import com.user.management.domain.Document;
import com.user.management.repository.DocumentRepository;
import com.user.management.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;


    @Override
    public Document save(Document document) {
        return documentRepository.save(document);
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
    public Page<Document> findAll(Pageable pageable) {
        return documentRepository.findAll(pageable);
    }

    @Override
    public Optional<Document> findById(Integer id) {
        final Optional<Document> document = documentRepository.findById(id);
        if (document.isEmpty()) {
            throw new ResourceNotFoundException("No document available with id = " + id);
        }
        return document;
    }
}

