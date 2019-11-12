package com.user.management.controller;

import com.user.management.domain.dto.DocumentDTO;
import com.user.management.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/documents")
@AllArgsConstructor
public class DocumentController {

    private DocumentService documentService;

    @PostMapping
    public ResponseEntity<?> create(DocumentDTO dto) {
        final DocumentDTO saved = documentService.save(dto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@PathVariable Integer id, DocumentDTO dto) {
        final DocumentDTO saved = documentService.save(dto);
        return ok(saved);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        documentService.deleteById(id);
    }

    @GetMapping
    Page<DocumentDTO> findAll(Pageable pageable) {
        return documentService.findAll(pageable);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ok(documentService.findById(id));
    }

}
