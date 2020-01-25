package com.educational.platform.userservice.mapper;

import com.educational.platform.userservice.dto.UserRegistrationDTO;
import com.educational.platform.userservice.dto.UserResponseDTO;
import com.educational.platform.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User toUser(UserRegistrationDTO userRegistration);

    UserResponseDTO toUserResponse(User user);
}
