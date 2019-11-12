package com.user.management.service;

import com.user.management.domain.dto.UserDTO;

public interface UserService {

    void delete(String username);

    UserDTO search(String username);
}
