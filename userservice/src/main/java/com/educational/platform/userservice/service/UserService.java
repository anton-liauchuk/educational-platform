package com.educational.platform.userservice.service;

import com.educational.platform.userservice.dto.UserRegistrationDTO;
import com.educational.platform.userservice.dto.UserResponseDTO;

import javax.servlet.http.HttpServletRequest;

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
    String signin(String username, String password);

    /**
     * Sign up.
     *
     * @param dto user registration dto
     * @return token
     */
    String signup(UserRegistrationDTO dto);

    /**
     * Deletes a user.
     *
     * @param username username
     */
    void delete(String username);

    /**
     * Searches a user by username.
     *
     * @param username username
     * @return user
     */
    UserResponseDTO search(String username);

    /**
     * Provides info about current user.
     *
     * @param req request
     * @return user
     */
    UserResponseDTO whoami(HttpServletRequest req);

    /**
     * Refreshes a token.
     *
     * @param username username
     * @return token
     */
    String refreshToken(String username);

}
