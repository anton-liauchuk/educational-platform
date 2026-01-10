package com.educational.platform.users;

import com.educational.platform.common.domain.AggregateRoot;
import com.educational.platform.users.registration.UserRegistrationCommand;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Collections;

/**
 * Represents User domain model.
 */
@Entity
@Table(name="custom_user")
public class User implements AggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String email;
    private String password;
    private Role role;

    // for JPA
    protected User() {
    }

    public User(UserRegistrationCommand command, PasswordEncoder passwordEncoder) {
        this.username = command.username();
        this.email = command.email();
        this.password = passwordEncoder.encode(command.password());
        this.role = Role.from(command.role());
    }

    public UserDTO toDTO() {
        return UserDTO.builder()
                .username(username)
                .email(email)
                .role(role.toDTO())
                .build();
    }

    public UserDetails toUserDetails() {
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(password)
                .authorities(Collections.singletonList(role))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
