package io.linuxtips.basicauth.controller;

import io.linuxtips.basicauth.model.User;
import io.linuxtips.basicauth.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return this.userService.save(user);
    }

    @GetMapping("/test-login")
    public String testuser(){
        return "logado";
    }
}
