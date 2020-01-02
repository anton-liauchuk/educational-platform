package com.user.management.documents.service;

import com.user.management.documents.domain.dto.DocumentDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public interface DocumentService {

    DocumentDTO create(@Valid @NotNull DocumentDTO document);

    // todo valid positive on id
    void delete(@Valid @Positive Integer id);

    DocumentDTO find(@Valid @Positive Integer id);

    void update(@Valid @NotNull DocumentDTO dto);
}
