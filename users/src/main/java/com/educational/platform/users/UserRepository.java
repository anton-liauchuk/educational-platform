package com.educational.platform.users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Represents User repository.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Returns whether an user with the given username exists.
     *
     * @param username must not be {@literal null}.
     * @return {@literal true} if an user with the given username exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@literal username} is {@literal null}.
     */
    boolean existsByUsername(String username);

    /**
     * Retrieves an user by its username.
     *
     * @param username must not be {@literal null}.
     * @return the user with the given username or {@literal Optional#empty()} if none found.
     * @throws IllegalArgumentException if {@literal username} is {@literal null}.
     */
    Optional<User> findByUsername(String username);

}
