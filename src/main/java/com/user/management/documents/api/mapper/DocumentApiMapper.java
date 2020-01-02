package com.user.management.documents.api.mapper;

import com.user.management.documents.api.dto.DocumentRequest;
import com.user.management.documents.api.dto.DocumentResponse;
import com.user.management.documents.domain.dto.DocumentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentApiMapper {

    @Mapping(target = "id", ignore = true)
    DocumentDTO toDTO(DocumentRequest documentRequest);

    DocumentResponse toResponse(DocumentDTO dto);

}
