package com.educational.platform.users.login;

import com.educational.platform.common.exception.UnprocessableEntityException;
import com.educational.platform.users.RoleDTO;
import com.educational.platform.users.User;
import com.educational.platform.users.UserRepository;
import com.educational.platform.users.registration.UserRegistrationCommand;
import com.educational.platform.users.security.JwtTokenProvider;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SignInCommandHandlerTest {

    @Mock
    private UserRepository repository;

    @Mock
    private JwtTokenProvider jwtTokenProvider;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private PasswordEncoder passwordEncoder;

    private SignInCommandHandler sut;

    @BeforeEach
    void setUp() {
        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        sut = spy(new SignInCommandHandler(jwtTokenProvider, repository, validator, authenticationManager));
    }

    @Test
    void handle_validCommand_signedIn() {
        // given
        final SignInCommand signInCommand = SignInCommand.builder()
                .username("username")
                .password("password")
                .build();
        final UserRegistrationCommand userRegistrationCommand = UserRegistrationCommand.builder()
                .email("email@gmail.com")
                .username("username")
                .password("password")
                .role(RoleDTO.ROLE_STUDENT)
                .build();
        final User existingUser = new User(userRegistrationCommand, passwordEncoder);
        when(repository.findByUsername("username")).thenReturn(Optional.of(existingUser));
        when(jwtTokenProvider.createToken(any(), any())).thenReturn("token");

        // when
        final String token = sut.handle(signInCommand);

        // then
        assertThat(token)
                .isEqualTo("token");
    }

    @Test
    void handle_invalidUsernamePassword_unprocessableEntityException() {
        // given
        final SignInCommand signInCommand = SignInCommand.builder()
                .username("username")
                .password("password")
                .build();
        doThrow(BadCredentialsException.class).when(authenticationManager).authenticate(any());

        // when
        final ThrowableAssert.ThrowingCallable handle = () -> sut.handle(signInCommand);

        // then
        assertThatExceptionOfType(UnprocessableEntityException.class).isThrownBy(handle);
    }

    @Test
    void handle_usernameIsEmpty_constraintViolationException() {
        // given
        final SignInCommand signInCommand = SignInCommand.builder()
                .password("password")
                .build();

        // when
        final ThrowableAssert.ThrowingCallable handle = () -> sut.handle(signInCommand);

        // then
        assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(handle);
    }

    @Test
    void handle_passwordIsEmpty_constraintViolationException() {
        // given
        final SignInCommand signInCommand = SignInCommand.builder()
                .username("username")
                .build();

        // when
        final ThrowableAssert.ThrowingCallable handle = () -> sut.handle(signInCommand);

        // then
        assertThatExceptionOfType(ConstraintViolationException.class).isThrownBy(handle);
    }
}
