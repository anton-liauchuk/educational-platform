package com.user.management.service;

import com.user.management.model.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DocumentService {

    Document save(Document document);

    void deleteById(Integer id);

    Page<Document> findAll(Pageable pageable);

    Optional<Document> findById(Integer id);
}
