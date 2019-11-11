package com.user.management.controller;

import com.user.management.domain.dto.DocumentDTO;
import com.user.management.mapper.DocumentMapper;
import com.user.management.domain.Document;
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
    private DocumentMapper documentMapper;

    @PostMapping
    public ResponseEntity<?> create(DocumentDTO dto) {
        final Document document = documentMapper.toDocument(dto);
        final Document saved = documentService.save(document);
        return new ResponseEntity<>(documentMapper.toDTO(saved), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@PathVariable Integer id, DocumentDTO dto) {
        final Document document = documentMapper.toDocument(dto);
        final Document saved = documentService.save(document);
        return ok(documentMapper.toDTO(saved));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        documentService.deleteById(id);
    }

    @GetMapping
    Page<DocumentDTO> findAll(Pageable pageable) {
        return documentService.findAll(pageable)
                .map(document -> documentMapper.toDTO(document));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ok(documentMapper.toDTO(documentService.findById(id).get()));
    }

}
