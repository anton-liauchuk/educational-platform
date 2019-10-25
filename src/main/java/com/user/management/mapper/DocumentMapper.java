package com.user.management.mapper;

import com.user.management.dto.DocumentDTO;
import com.user.management.model.Document;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    Document toDocument(DocumentDTO documentDTO);

    DocumentDTO toDTO(Document document);

}
