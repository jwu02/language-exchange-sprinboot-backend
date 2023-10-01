package com.example.languageexchangebackend.controller;

import com.example.languageexchangebackend.dto.LoginDTO;
import com.example.languageexchangebackend.dto.UserDTO;
import com.example.languageexchangebackend.model.User;
import com.example.languageexchangebackend.repository.UserRepository;
import com.example.languageexchangebackend.response.LoginMessage;
import com.example.languageexchangebackend.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    public UserController(
            UserService userService,
            UserRepository userRepository
    ) {
        // wiring beans / inject dependencies (alternative to @Autowired)
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        User user = userService.registerUser(userDTO);
        return user;
    }

    @GetMapping("/register/email-exists")
    public ResponseEntity<Boolean> emailExistsValidation(@RequestParam String value) {
        // existing user with emails matching query
        User user = userRepository.findByEmail(value);
        return ResponseEntity.ok(user != null);
    }

    @GetMapping("/register/username-exists")
    public ResponseEntity<Boolean> usernameExistsValidation(@RequestParam String value) {
        // existing user with emails matching query
        User user = userRepository.findByUsername(value);
        return ResponseEntity.ok(user != null);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDTO loginDTO) {
        LoginMessage loginMessage = userService.loginUser(loginDTO);
        return ResponseEntity.ok(loginMessage);
    }
}
