package com.user.management.mapper;

import com.user.management.domain.Document;
import com.user.management.domain.dto.DocumentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    Document toDocument(DocumentDTO documentDTO);

    DocumentDTO toDTO(Document document);

    void updateDocumentFromDTO(DocumentDTO documentDTO, @MappingTarget Document document);

}
