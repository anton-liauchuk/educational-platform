package com.educational.platform.users.security;

import com.educational.platform.users.login.SignInCommandHandler;
import com.educational.platform.users.registration.UserRegistrationCommandHandler;
import jakarta.validation.Valid;

import com.educational.platform.users.login.SignInCommand;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educational.platform.users.registration.UserRegistrationCommand;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

	private final UserRegistrationCommandHandler userRegistrationCommandHandler;
	private final SignInCommandHandler signInCommandHandler;

    public UserController(UserRegistrationCommandHandler userRegistrationCommandHandler, SignInCommandHandler signInCommandHandler) {
        this.userRegistrationCommandHandler = userRegistrationCommandHandler;
        this.signInCommandHandler = signInCommandHandler;
    }

    @PostMapping("/sign-up")
	public String signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
		var command = UserRegistrationCommand
				.builder()
				.role(signUpRequest.role())
				.email(signUpRequest.email())
				.username(signUpRequest.username())
				.password(signUpRequest.password())
				.build();

		return userRegistrationCommandHandler.handle(command);
	}

	@PostMapping("/sign-in")
	public String signIn(@Valid @RequestBody SignInRequest signInRequest) {
		var command = SignInCommand
				.builder()
				.username(signInRequest.username())
				.password(signInRequest.password())
				.build();

		return signInCommandHandler.handle(command);
	}
}
