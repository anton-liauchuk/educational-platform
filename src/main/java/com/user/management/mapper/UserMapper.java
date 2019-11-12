package com.user.management.mapper;

import com.user.management.domain.User;
import com.user.management.domain.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDTO userDataDTO);

    UserDTO toDTO(User user);

}
