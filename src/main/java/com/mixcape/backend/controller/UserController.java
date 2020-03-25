package com.mixcape.backend.controller;

import com.mixcape.backend.entity.User;
import com.mixcape.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User create(@RequestParam String username, String password) {
        return userService.join(username, password);
    }

    @GetMapping(value = "/me")
    public User getMe(@RequestAttribute User user) {
        return user;
    }

    @PutMapping(value = "/me")
    public User updatePassword(@RequestAttribute User user, @RequestParam String password) {
        return userService.updatePassword(user.getId(), password);
    }

    @DeleteMapping
    public void Withdraw(@RequestAttribute User user) {
        userService.withdraw(user.getId());
    }
}