package com.user.management.service;

import com.user.management.domain.dto.TagDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public interface TagService {

    TagDTO create(@Valid @NotNull TagDTO document);

    void delete(Integer id);

    TagDTO find(Integer id);
}
