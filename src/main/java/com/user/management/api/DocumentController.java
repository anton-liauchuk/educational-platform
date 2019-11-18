package com.user.management.api;

import com.user.management.domain.dto.DocumentDTO;
import com.user.management.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
@Validated
class DocumentController {

    private final DocumentService documentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    // todo DocumentResponse as return type
    DocumentDTO create(@Valid @RequestBody DocumentDTO dto) {
        return documentService.create(dto);
    }

    @PutMapping(value = "/{id}")
    void update(@Valid @Positive @PathVariable Integer id, @Valid @RequestBody DocumentDTO dto) {
        documentService.update(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@Valid @Positive @PathVariable Integer id) {
        documentService.deleteById(id);
    }

    @GetMapping(value = "/{id}")
    DocumentDTO findById(@Valid @Positive @PathVariable Integer id) {
        return documentService.findById(id);
    }

}
