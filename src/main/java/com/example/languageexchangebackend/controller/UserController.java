package com.example.languageexchangebackend.controller;

import com.example.languageexchangebackend.dto.LoginDTO;
import com.example.languageexchangebackend.dto.UserDTO;
import com.example.languageexchangebackend.model.User;
import com.example.languageexchangebackend.response.LoginMessage;
import com.example.languageexchangebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDTO userDTO) {
        User user = userService.registerUser(userDTO);
        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        LoginMessage loginMessage = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginMessage);
    }
}
