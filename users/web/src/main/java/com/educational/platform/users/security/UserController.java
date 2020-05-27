package com.educational.platform.users.security;

import javax.validation.Valid;

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
}
