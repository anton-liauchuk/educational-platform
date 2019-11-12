package com.user.management.controller;

import com.user.management.domain.dto.UserDTO;
import com.user.management.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @DeleteMapping(value = "/{username}")
    public String delete(@PathVariable String username) {
        userService.delete(username);
        return username;
    }

    @GetMapping(value = "/{username}")
    public UserDTO search(@PathVariable String username) {
        return userService.search(username);
    }
}
