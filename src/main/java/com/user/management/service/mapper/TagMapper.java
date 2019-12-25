package com.user.management.service.mapper;

import com.user.management.domain.Tag;
import com.user.management.domain.dto.TagDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TagMapper {

    Tag toTag(TagDTO tagDTO);

    TagDTO toDTO(Tag tag);

}
