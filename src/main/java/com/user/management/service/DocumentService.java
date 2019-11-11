package com.user.management.service;

import com.user.management.domain.Document;
import com.user.management.domain.dto.DocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DocumentService {

    DocumentDTO save(Document document);

    void deleteById(Integer id);

    Page<Document> findAll(Pageable pageable);

    Optional<DocumentDTO> findById(Integer id);
}
