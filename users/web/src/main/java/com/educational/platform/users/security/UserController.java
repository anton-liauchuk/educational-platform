package com.educational.platform.users.security;

import javax.validation.Valid;

import com.educational.platform.users.login.SignInCommand;
import com.educational.platform.users.login.SignInCommandHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educational.platform.users.registration.UserRegistrationCommand;
import com.educational.platform.users.registration.UserRegistrationCommandHandler;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

	private final UserRegistrationCommandHandler registrationCommandHandler;
	private final SignInCommandHandler signInCommandHandler;

	@PostMapping("/sign-up")
	public String signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
		UserRegistrationCommand command = UserRegistrationCommand
				.builder()
				.role(signUpRequest.getRole())
				.email(signUpRequest.getEmail())
				.username(signUpRequest.getUsername())
				.password(signUpRequest.getPassword())
				.build();

		return registrationCommandHandler.handle(command);
	}

	@PostMapping("/sign-in")
	public String signIn(@Valid @RequestBody SignInRequest signInRequest) {
		SignInCommand command = SignInCommand
				.builder()
				.username(signInRequest.getUsername())
				.password(signInRequest.getPassword())
				.build();

		return signInCommandHandler.handle(command);
	}
}
