package com.educational.platform.users.repository;

import com.educational.platform.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    User findByUsername(String username);

    void deleteByUsername(String username);

}
