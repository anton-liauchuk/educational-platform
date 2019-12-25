package com.user.management.api;

import com.user.management.api.dto.TagApiDTO;
import com.user.management.api.mapper.TagApiMapper;
import com.user.management.domain.dto.TagDTO;
import com.user.management.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/tags")
@RequiredArgsConstructor
class TagController {

    private final TagService tagService;
    private final TagApiMapper mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    TagApiDTO create(@Valid @RequestBody TagApiDTO request) {
        final TagDTO convertedRequest = mapper.toDTO(request);
        final TagDTO createdTag = tagService.create(convertedRequest);
        return mapper.toApiDTO(createdTag);
    }

    @DeleteMapping(value = "/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable String name) {
        tagService.delete(name);
    }

    @GetMapping(value = "/{name}")
    TagApiDTO findByName(@PathVariable String name) {
        var dto = tagService.find(name);
        return mapper.toApiDTO(dto);
    }

}
