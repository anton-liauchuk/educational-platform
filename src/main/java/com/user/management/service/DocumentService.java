package com.user.management.service;

import com.user.management.domain.dto.DocumentDTO;

public interface DocumentService {

    DocumentDTO create(DocumentDTO document);

    void deleteById(Integer id);

    DocumentDTO findById(Integer id);

    void update(Integer id, DocumentDTO dto);
}
