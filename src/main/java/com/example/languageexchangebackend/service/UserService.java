package com.example.languageexchangebackend.service;

import com.example.languageexchangebackend.dto.LoginDTO;
import com.example.languageexchangebackend.dto.UserDTO;
import com.example.languageexchangebackend.model.User;
import com.example.languageexchangebackend.response.LoginMessage;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    @Transactional
    User registerUser(UserDTO userDTO);
    LoginMessage loginUser(LoginDTO loginDTO);
}
