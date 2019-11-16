package com.user.management.service.impl;

import com.user.management.domain.dto.UserDTO;
import com.user.management.mapper.UserMapper;
import com.user.management.repository.UserRepository;
import com.user.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public void delete(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public UserDTO search(String username) {
        return userMapper.toDTO(userRepository.findByUsername(username));
    }
}
