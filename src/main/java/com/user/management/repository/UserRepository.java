package com.user.management.repository;

import com.user.management.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    void deleteByUsername(String username);

}
