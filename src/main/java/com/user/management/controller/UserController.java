package com.user.management.controller;

import com.user.management.domain.dto.UserDTO;
import com.user.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private UserService userService;

    @DeleteMapping(value = "/{username}")
    public void delete(@PathVariable String username) {
        userService.delete(username);
    }

    @GetMapping(value = "/{username}")
    public UserDTO search(@PathVariable String username) {
        return userService.search(username);
    }
}
