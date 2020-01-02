package com.user.management.documents.service.mapper;

import com.user.management.documents.domain.Document;
import com.user.management.documents.domain.dto.DocumentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    Document toDocument(DocumentDTO documentDTO);

    DocumentDTO toDTO(Document document);

    @Mapping(ignore = true, target = "id")
    void updateDocumentFromDTO(DocumentDTO documentDTO, @MappingTarget Document document);

}
