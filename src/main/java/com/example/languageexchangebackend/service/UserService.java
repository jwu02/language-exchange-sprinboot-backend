package com.example.languageexchangebackend.service;

import com.example.languageexchangebackend.dto.LoginDTO;
import com.example.languageexchangebackend.dto.UserDTO;
import com.example.languageexchangebackend.model.User;
import com.example.languageexchangebackend.response.LoginMessage;

public interface UserService {
    String registerUser(UserDTO userDTO);
    LoginMessage loginUser(LoginDTO loginDTO);
}
