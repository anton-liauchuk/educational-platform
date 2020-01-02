package com.user.management.service.impl;

import com.user.management.api.exception.ResourceNotFoundException;
import com.user.management.domain.Tag;
import com.user.management.domain.dto.TagDTO;
import com.user.management.repository.TagRepository;
import com.user.management.service.TagService;
import com.user.management.service.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@Service
@Transactional
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper mapper;


    // todo review annotations in Impl
    @Override
    public TagDTO create(@Valid @NotNull TagDTO dto) {
        final Tag tag = mapper.toTag(dto);
        final Tag saved = tagRepository.save(tag);
        return mapper.toDTO(saved);
    }

    @Override
    public void delete(@Valid @NotBlank String name) {
        final int result = tagRepository.deleteByName(name);
        if (result == 0) {
            throw new ResourceNotFoundException(String.format("No %s entity with name %s exists!", Tag.class, name));
        }
    }

    @Override
    public TagDTO find(@Valid @NotBlank String name) {
        var tag = tagRepository.findByName(name)
                .orElseThrow(() -> new ResourceNotFoundException("Tag not found."));
        return mapper.toDTO(tag);
    }
}
