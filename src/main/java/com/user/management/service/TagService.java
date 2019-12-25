package com.user.management.service;

import com.user.management.domain.dto.TagDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface TagService {

    TagDTO create(@Valid @NotNull TagDTO tag);

    void delete(@Valid @NotBlank String name);

    TagDTO find(@Valid @NotBlank String name);
}
