package com.educational.platform.users.service.impl;

import com.educational.platform.common.exception.UnprocessableEntityException;
import com.educational.platform.users.mapper.UserMapper;
import com.educational.platform.users.model.User;
import com.educational.platform.users.model.dto.UserRegistrationDTO;
import com.educational.platform.users.repository.UserRepository;
import com.educational.platform.users.security.JwtTokenProvider;
import com.educational.platform.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final UserMapper mapper;

    @Override
    public String signIn(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    }

    @Override
    public String signUp(UserRegistrationDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UnprocessableEntityException(String.format("Username: [%s] is already in use", dto.getUsername()));
        }

        final User user = mapper.toUser(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        return jwtTokenProvider.createToken(dto.getUsername(), dto.getRoles());
    }

}
