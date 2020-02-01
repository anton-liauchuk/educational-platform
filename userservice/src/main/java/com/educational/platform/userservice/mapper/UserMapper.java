package com.educational.platform.userservice.mapper;

import com.educational.platform.userservice.model.dto.UserRegistrationDTO;
import com.educational.platform.userservice.model.dto.UserResponseDTO;
import com.educational.platform.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User toUser(UserRegistrationDTO userRegistration);

    UserResponseDTO toUserResponse(User user);
}
