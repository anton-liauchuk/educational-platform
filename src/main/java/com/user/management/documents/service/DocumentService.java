package com.user.management.documents.service;

import com.user.management.documents.domain.dto.DocumentDTO;

/**
 * Document application service.
 */
public interface DocumentService {

    DocumentDTO create(DocumentDTO document);

    // todo valid positive on id
    void delete(Integer id);

    DocumentDTO find(Integer id);

    void update(DocumentDTO dto);
}
