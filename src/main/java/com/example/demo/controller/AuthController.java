package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.entity.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")

public class AuthController {
    // hm key:string value:user
    private Map<String, User> users = new HashMap<>();

    @PostMapping("/register")
    // register --> params: reg request
    public String register(@RequestBody LoginRequest request) {

        // create new user
        User user = new User();
        user.setUsername(request.username);
        user.setPassword(request.password);

        // put user in users
        users.put(user.getUsername(), user);
        return "User registered";
    }

    @PostMapping("/login")
    // login --> params: login request
    public String login(@RequestBody LoginRequest request) {

        // get user from users using username
        User user = users.get(request.username);

        // user DNE
        if (user == null) {
            return "User not found";
        }

        // incorrect user password
        if (!user.getPassword().equals(request.password)) {
            return "Wrong password";
        }

        // correct user/pass combo
        return "Login successful";
    }
}
