package com.educational.platform.users.mapper;

import com.educational.platform.users.model.dto.UserRegistrationDTO;
import com.educational.platform.users.model.dto.UserResponseDTO;
import com.educational.platform.users.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User toUser(UserRegistrationDTO userRegistration);

    UserResponseDTO toUserResponse(User user);
}
