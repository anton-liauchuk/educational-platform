package com.user.management.documents.api;

import com.user.management.documents.api.dto.DocumentRequest;
import com.user.management.documents.api.dto.DocumentResponse;
import com.user.management.documents.api.mapper.DocumentApiMapper;
import com.user.management.documents.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;
    private final DocumentApiMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DocumentResponse create(@RequestBody DocumentRequest request) {
        var document = mapper.toDTO(request);
        var createdDocument = documentService.create(document);
        return mapper.toResponse(createdDocument);
    }

    @PutMapping(value = "/{id}")
    public void update(@PathVariable Integer id, @RequestBody DocumentRequest request) {
        var document = mapper.toDTO(request);
        // todo recheck solution
        document.setId(id);
        documentService.update(document);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        documentService.delete(id);
    }

    @GetMapping(value = "/{id}")
    public DocumentResponse findById(@PathVariable Integer id) {
        var document = documentService.find(id);
        return mapper.toResponse(document);
    }

}
