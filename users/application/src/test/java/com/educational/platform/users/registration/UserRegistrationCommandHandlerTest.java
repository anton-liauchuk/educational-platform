package com.educational.platform.users.registration;

import com.educational.platform.common.exception.UnprocessableEntityException;
import com.educational.platform.users.Role;
import com.educational.platform.users.RoleDTO;
import com.educational.platform.users.User;
import com.educational.platform.users.UserRepository;
import com.educational.platform.users.integration.event.UserCreatedIntegrationEvent;
import com.educational.platform.users.security.JwtTokenProvider;
import org.assertj.core.api.ThrowableAssert;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.GenericEventMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRegistrationCommandHandlerTest {

    @Mock
    private UserRepository repository;

    @Mock
    private PlatformTransactionManager transactionManager;

    @Mock
    private TransactionTemplate transactionTemplate;

    @Mock
    private EventBus eventBus;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    private UserRegistrationCommandHandler sut;

    @BeforeEach
    void setUp() {
        transactionTemplate = new TransactionTemplate(transactionManager);
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        sut = new UserRegistrationCommandHandler(transactionTemplate, passwordEncoder, jwtTokenProvider, repository, eventBus, validator);
    }

    @Test
    void handle_validCommand_userCreated() {
        // given
        final UserRegistrationCommand userRegistrationCommand = UserRegistrationCommand.builder()
                .email("email@gmail.com")
                .username("username")
                .password("password")
                .role(RoleDTO.ROLE_STUDENT)
                .build();
        when(repository.existsByUsername("username")).thenReturn(false);

        // when
        sut.handle(userRegistrationCommand);

        // then
        final ArgumentCaptor<User> argument = ArgumentCaptor.forClass(User.class);
        verify(repository).save(argument.capture());
        final User user = argument.getValue();
        assertThat(user)
                .hasFieldOrPropertyWithValue("username", "username")
                .hasFieldOrPropertyWithValue("email", "email@gmail.com")
                .hasFieldOrPropertyWithValue("role", Role.ROLE_STUDENT);

        final ArgumentCaptor<GenericEventMessage<UserCreatedIntegrationEvent>> eventArgument = ArgumentCaptor.forClass(GenericEventMessage.class);
        verify(eventBus).publish(eventArgument.capture());
        final UserCreatedIntegrationEvent event = eventArgument.getValue().getPayload();
        assertThat(event)
                .hasFieldOrPropertyWithValue("username", "username")
                .hasFieldOrPropertyWithValue("email", "email@gmail.com");
    }

    @Test
    void handle_usernameAlreadyExists_unprocessableEntityException() {
        // given
        final UserRegistrationCommand userRegistrationCommand = UserRegistrationCommand.builder()
                .email("email@gmail.com")
                .username("username")
                .password("password")
                .role(RoleDTO.ROLE_STUDENT)
                .build();
        when(repository.existsByUsername("username")).thenReturn(true);

        // when
        final ThrowableAssert.ThrowingCallable handle = () -> sut.handle(userRegistrationCommand);

        // then
        assertThatExceptionOfType(UnprocessableEntityException.class).isThrownBy(handle);
    }

    @Test
    void handle_roleIsEmpty_constraintViolationException() {
        // given
        final UserRegistrationCommand userRegistrationCommand = UserRegistrationCommand.builder()
                .email("email@gmail.com")
                .username("username")
                .password("password")
                .role(null)
                .build();

        // when
        final ThrowableAssert.ThrowingCallable handle = () -> sut.handle(userRegistrationCommand);

        // then
        assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(handle);
    }

    @Test
    void handle_usernameIsEmpty_constraintViolationException() {
        // given
        final UserRegistrationCommand userRegistrationCommand = UserRegistrationCommand.builder()
                .email("email@gmail.com")
                .username(null)
                .password("password")
                .role(RoleDTO.ROLE_STUDENT)
                .build();

        // when
        final ThrowableAssert.ThrowingCallable handle = () -> sut.handle(userRegistrationCommand);

        // then
        assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(handle);
    }

    @Test
    void handle_emailIsEmpty_constraintViolationException() {
        // given
        final UserRegistrationCommand userRegistrationCommand = UserRegistrationCommand.builder()
                .email(null)
                .username("username")
                .password("password")
                .role(RoleDTO.ROLE_STUDENT)
                .build();

        // when
        final ThrowableAssert.ThrowingCallable handle = () -> sut.handle(userRegistrationCommand);

        // then
        assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(handle);
    }

    @Test
    void handle_passwordIsEmpty_constraintViolationException() {
        // given
        final UserRegistrationCommand userRegistrationCommand = UserRegistrationCommand.builder()
                .email("email@gmail.com")
                .username("username")
                .password(null)
                .role(RoleDTO.ROLE_STUDENT)
                .build();

        // when
        final ThrowableAssert.ThrowingCallable handle = () -> sut.handle(userRegistrationCommand);

        // then
        assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(handle);
    }
}
