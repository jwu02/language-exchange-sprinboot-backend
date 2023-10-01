package com.example.languageexchangebackend.service.impl;

import com.example.languageexchangebackend.dto.LoginDTO;
import com.example.languageexchangebackend.dto.UserDTO;
import com.example.languageexchangebackend.model.User;
import com.example.languageexchangebackend.repository.UserRepository;
import com.example.languageexchangebackend.response.LoginMessage;
import com.example.languageexchangebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User registerUser(UserDTO userDTO) {
        User user = new User(
                userDTO.getId(),
                userDTO.getEmail(),
                userDTO.getUsername(),
                this.passwordEncoder.encode(userDTO.getPassword()),
                userDTO.getDob(),
                userDTO.getGender(),
                userDTO.getSelfIntroduction()
        );

        userRepository.save(user);

        return user;
    }

    @Override
    public LoginMessage loginUser(LoginDTO loginDTO) {
        String msg = "";
        User user1 = userRepository.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginMessage("Login success", true, user.get());
                } else {
                    return new LoginMessage("Login failed.", false);
                }
            } else {
                return new LoginMessage("Incorrect password, please try again.", false);
            }
        } else {
            return new LoginMessage("Email doesn't exist, please register an account.", false);
        }
    }
}
