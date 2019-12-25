package com.user.management.service;

import com.user.management.domain.dto.DocumentDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface DocumentService {

    DocumentDTO create(@Valid @NotNull DocumentDTO document);

    // todo valid positive on id
    void delete(Integer id);

    DocumentDTO find(Integer id);

    void update(@Valid @NotNull DocumentDTO dto);
}
