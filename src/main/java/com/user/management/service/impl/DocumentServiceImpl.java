package com.user.management.service.impl;

import com.user.management.model.Document;
import com.user.management.repository.DocumentRepository;
import com.user.management.service.DocumentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;


    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Document save(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public void deleteById(Integer id) {
        documentRepository.deleteById(id);
    }

    @Override
    public Page<Document> findAll(Pageable pageable) {
        return documentRepository.findAll(pageable);
    }
}

