package com.user.management.service;

import com.user.management.domain.dto.DocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DocumentService {

    DocumentDTO save(DocumentDTO document);

    void deleteById(Integer id);

    Page<DocumentDTO> findAll(Pageable pageable);

    DocumentDTO findById(Integer id);
}
