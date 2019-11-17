package com.user.management.api;

import com.user.management.domain.dto.DocumentDTO;
import com.user.management.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    // todo DocumentResponse as return type
    public DocumentDTO create(@RequestBody DocumentDTO dto) {
        return documentService.save(dto);
    }

    @PutMapping(value = "/{id}")
    public void put(@PathVariable Integer id, @RequestBody DocumentDTO dto) {
        documentService.save(id, dto);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        documentService.deleteById(id);
    }

    @GetMapping(value = "/{id}")
    public DocumentDTO findById(@PathVariable Integer id) {
        return documentService.findById(id);
    }

}
