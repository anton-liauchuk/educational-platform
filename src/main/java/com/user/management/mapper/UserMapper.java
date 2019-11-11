package com.user.management.mapper;

import com.user.management.dto.UserDataDTO;
import com.user.management.dto.UserResponseDTO;
import com.user.management.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDataDTO userDataDTO);

    UserDataDTO toDTO(User user);

    UserResponseDTO toUserResponseDTO(User user);

}
