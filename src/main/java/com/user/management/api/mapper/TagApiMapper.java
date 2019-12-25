package com.user.management.api.mapper;

import com.user.management.api.dto.TagApiDTO;
import com.user.management.domain.dto.TagDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagApiMapper {

    @Mapping(target = "id", ignore = true)
    TagDTO toDTO(TagApiDTO tagApiDTO);

    TagApiDTO toApiDTO(TagDTO dto);

}
