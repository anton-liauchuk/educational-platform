package com.user.management.api;

import com.user.management.api.dto.DocumentRequest;
import com.user.management.api.dto.DocumentResponse;
import com.user.management.api.mapper.DocumentApiMapper;
import com.user.management.domain.dto.DocumentDTO;
import com.user.management.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
class DocumentController {

    private final DocumentService documentService;
    private final DocumentApiMapper mapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    DocumentResponse create(@Valid @RequestBody DocumentRequest request) {
        final DocumentDTO convertedRequest = mapper.toDTO(request);
        final DocumentDTO createdDocument = documentService.create(convertedRequest);
        return mapper.toResponse(createdDocument);
    }

    @PutMapping(value = "/{id}")
    void update(@Valid @Positive @PathVariable Integer id, @Valid @RequestBody DocumentRequest request) {
        final DocumentDTO dto = mapper.toDTO(request);
        // todo recheck solution
        dto.setId(id);
        documentService.update(dto);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@Valid @Positive @PathVariable Integer id) {
        documentService.delete(id);
    }

    @GetMapping(value = "/{id}")
    DocumentResponse findById(@Valid @Positive @PathVariable Integer id) {
        final DocumentDTO dto = documentService.find(id);
        return mapper.toResponse(dto);
    }

}
