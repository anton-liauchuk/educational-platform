package com.educational.platform.users;

import com.educational.platform.common.domain.AggregateRoot;
import com.educational.platform.users.registration.UserRegistrationCommand;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Represents User domain model.
 */
@Entity
public class User implements AggregateRoot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String email;
    private String password;
    private Role role;

    // for JPA
    private User() {
    }

    public User(UserRegistrationCommand command, PasswordEncoder passwordEncoder) {
        this.username = command.getUsername();
        this.email = command.getEmail();
        this.password = passwordEncoder.encode(command.getPassword());
        this.role = Role.from(command.getRole());
    }

    public UserDTO toDTO() {
        return UserDTO.builder()
                .username(username)
                .email(email)
                .role(role.toDTO())
                .build();
    }
}
