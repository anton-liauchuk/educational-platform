package com.educational.platform.userservice.service.impl;

import com.educational.platform.userservice.model.dto.UserRegistrationDTO;
import com.educational.platform.userservice.model.dto.UserResponseDTO;
import com.educational.platform.userservice.exception.CustomException;
import com.educational.platform.userservice.mapper.UserMapper;
import com.educational.platform.userservice.model.User;
import com.educational.platform.userservice.repository.UserRepository;
import com.educational.platform.userservice.security.JwtTokenProvider;
import com.educational.platform.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public String signUp(UserRegistrationDTO dto) {
        final User user = mapper.toUser(dto);
        if (!userRepository.existsByUsername(dto.getUsername())) {
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
            userRepository.save(user);
            return jwtTokenProvider.createToken(dto.getUsername(), dto.getRoles());
        } else {
            throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @Override
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public UserResponseDTO search(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
        }
        return mapper.toUserResponse(user);
    }

    @Override
    public UserResponseDTO whoAmI(HttpServletRequest req) {
        final User user = userRepository.findByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
        return mapper.toUserResponse(user);
    }

    @Override
    public String refreshToken(String username) {
        return jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRoles());
    }

}
