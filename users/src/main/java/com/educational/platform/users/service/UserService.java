package com.educational.platform.users.service;

import com.educational.platform.users.model.dto.UserRegistrationDTO;

/**
 * Represents User Service.
 */
public interface UserService {

    /**
     * Sign in.
     *
     * @param username username
     * @param password password
     * @return token
     */
    String signIn(String username, String password);

    /**
     * Sign up.
     *
     * @param dto user registration dto
     * @return token
     * @throws com.educational.platform.common.exception.UnprocessableEntityException if username is use
     */
    String signUp(UserRegistrationDTO dto);

}
