package com.user.management.api.mapper;

import com.user.management.api.dto.DocumentRequest;
import com.user.management.api.dto.DocumentResponse;
import com.user.management.domain.dto.DocumentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentApiMapper {

    @Mapping(target = "id", ignore = true)
    DocumentDTO toDTO(DocumentRequest documentRequest);

    DocumentResponse toResponse(DocumentDTO dto);

}
