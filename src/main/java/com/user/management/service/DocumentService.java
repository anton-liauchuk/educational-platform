package com.user.management.service;

import com.user.management.domain.dto.DocumentDTO;

public interface DocumentService {

    DocumentDTO save(DocumentDTO document);

    void deleteById(Integer id);

    DocumentDTO findById(Integer id);
}
