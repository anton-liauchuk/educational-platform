package com.educational.platform.users.registration;

import com.educational.platform.common.exception.UnprocessableEntityException;
import com.educational.platform.users.Role;
import com.educational.platform.users.User;
import com.educational.platform.users.UserDTO;
import com.educational.platform.users.UserRepository;
import com.educational.platform.users.integration.event.UserCreatedIntegrationEvent;
import com.educational.platform.users.security.JwtTokenProvider;

import org.springframework.context.ApplicationEventPublisher;
import jakarta.annotation.Nonnull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Represents User Registration command handler which creates user in system by provided info in command.
 */
@Component
public class UserRegistrationCommandHandler {

    private final TransactionTemplate transactionTemplate;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository repository;
    private final ApplicationEventPublisher eventPublisher;
    private final Validator validator;

    public UserRegistrationCommandHandler(TransactionTemplate transactionTemplate, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, UserRepository repository, ApplicationEventPublisher eventPublisher, Validator validator) {
        this.transactionTemplate = transactionTemplate;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        this.validator = validator;
    }

    /**
     * Handles user registration command. Creates and saves user from provided registration.
     *
     * @param command command
     * @return token
     * @throws ConstraintViolationException validation errors
     * @throws UnprocessableEntityException if username is already in use
     */
    @Nonnull
    public String handle(UserRegistrationCommand command) {
        final User user = transactionTemplate.execute(transactionStatus -> {
            final Set<ConstraintViolation<UserRegistrationCommand>> violations = validator.validate(command);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }

            if (repository.existsByUsername(command.username())) {
                throw new UnprocessableEntityException(String.format("Username: [%s] is already in use", command.username()));
            }

            final User newUser = new User(command, passwordEncoder);
            repository.save(newUser);
            return newUser;
        });

        final UserDTO dto = Objects.requireNonNull(user).toDTO();
        eventPublisher.publishEvent(new UserCreatedIntegrationEvent(dto.username(), dto.email()));

        return jwtTokenProvider.createToken(dto.username(), Collections.singletonList(Role.from(dto.role())));
    }
}
