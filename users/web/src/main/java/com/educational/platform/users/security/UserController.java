package com.educational.platform.users.security;

import javax.validation.Valid;

import com.educational.platform.users.login.SignInCommand;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educational.platform.users.registration.UserRegistrationCommand;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

	private final CommandGateway commandGateway;

	@PostMapping("/sign-up")
	public String signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
		UserRegistrationCommand command = UserRegistrationCommand
				.builder()
				.role(signUpRequest.getRole())
				.email(signUpRequest.getEmail())
				.username(signUpRequest.getUsername())
				.password(signUpRequest.getPassword())
				.build();

		return commandGateway.sendAndWait(command);
	}

	@PostMapping("/sign-in")
	public String signIn(@Valid @RequestBody SignInRequest signInRequest) {
		SignInCommand command = SignInCommand
				.builder()
				.username(signInRequest.getUsername())
				.password(signInRequest.getPassword())
				.build();

		return commandGateway.sendAndWait(command);
	}
}
