package com.user.management.controller;

import com.user.management.dto.DocumentDTO;
import com.user.management.model.Document;
import com.user.management.service.DocumentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> create(DocumentDTO dto) {
        final Document document = modelMapper.map(dto, Document.class);
        final Document saved = documentService.save(document);
        return new ResponseEntity<>(modelMapper.map(saved, DocumentDTO.class), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@PathVariable Integer id, DocumentDTO dto) {
        final Document document = modelMapper.map(dto, Document.class);
        final Document saved = documentService.save(document);
        return ok(modelMapper.map(saved, DocumentDTO.class));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id) {
        documentService.deleteById(id);
    }

    @GetMapping
    Page<DocumentDTO> findAll(Pageable pageable) {
        return documentService.findAll(pageable)
                .map(document -> modelMapper.map(document, DocumentDTO.class));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        return ok(modelMapper.map(documentService.findById(id).get(), DocumentDTO.class));
    }

}
