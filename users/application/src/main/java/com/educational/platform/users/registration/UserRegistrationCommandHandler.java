package com.educational.platform.users.registration;

import com.educational.platform.common.exception.UnprocessableEntityException;
import com.educational.platform.users.Role;
import com.educational.platform.users.User;
import com.educational.platform.users.UserDTO;
import com.educational.platform.users.UserRepository;
import com.educational.platform.users.integration.event.UserCreatedIntegrationEvent;
import com.educational.platform.users.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.GenericEventMessage;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

/**
 * Represents User Registration command handler which creates user in system by provided info in command.
 */
@RequiredArgsConstructor
@Component
public class UserRegistrationCommandHandler {

    private final TransactionTemplate transactionTemplate;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository repository;
    private final EventBus eventBus;
    private final Validator validator;

    /**
     * Handles user registration command. Creates and saves user from provided registration.
     *
     * @param command command
     * @return token
     * @throws ConstraintViolationException validation errors
     * @throws UnprocessableEntityException if username is already in use
     */
    @CommandHandler
    @NonNull
    public String handle(UserRegistrationCommand command) {
        final User user = transactionTemplate.execute(transactionStatus -> {
            final Set<ConstraintViolation<UserRegistrationCommand>> violations = validator.validate(command);
            if (!violations.isEmpty()) {
                throw new ConstraintViolationException(violations);
            }

            if (repository.existsByUsername(command.getUsername())) {
                throw new UnprocessableEntityException(String.format("Username: [%s] is already in use", command.getUsername()));
            }

            final User newUser = new User(command, passwordEncoder);
            repository.save(newUser);
            return newUser;
        });

        final UserDTO dto = Objects.requireNonNull(user).toDTO();
        eventBus.publish(GenericEventMessage.asEventMessage(new UserCreatedIntegrationEvent(dto.getUsername(), dto.getEmail())));

        return jwtTokenProvider.createToken(dto.getUsername(), Collections.singletonList(Role.from(dto.getRole())));
    }
}
