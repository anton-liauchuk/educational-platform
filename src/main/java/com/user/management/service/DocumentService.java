package com.user.management.service;

import com.user.management.domain.dto.DocumentDTO;

public interface DocumentService {

    DocumentDTO create(DocumentDTO document);

    void delete(Integer id);

    DocumentDTO find(Integer id);

    void update(DocumentDTO dto);
}
