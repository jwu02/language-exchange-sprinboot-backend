package com.example.languageexchangebackend.service.impl;

import com.example.languageexchangebackend.dto.LoginDTO;
import com.example.languageexchangebackend.dto.UserLanguageDTO;
import com.example.languageexchangebackend.dto.UserDTO;
import com.example.languageexchangebackend.model.Language;
import com.example.languageexchangebackend.model.UserLanguage;
import com.example.languageexchangebackend.model.User;
import com.example.languageexchangebackend.model.UserLanguageType;
import com.example.languageexchangebackend.repository.LanguageRepository;
import com.example.languageexchangebackend.repository.UserLanguageRepository;
import com.example.languageexchangebackend.repository.UserRepository;
import com.example.languageexchangebackend.response.LoginMessage;
import com.example.languageexchangebackend.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private LanguageRepository languageRepository;
    private UserLanguageRepository userLanguageRepository;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            LanguageRepository languageRepository,
            UserLanguageRepository userLanguageRepository
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.languageRepository = languageRepository;
        this.userLanguageRepository = userLanguageRepository;
    }

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

        for (UserLanguageDTO tlDto: userDTO.getTeachLanguages()) {
            Optional<Language> languageObj = languageRepository.findById(tlDto.getLanguageId());
            if (languageObj.isPresent()) {
                UserLanguage newTeachLanguage = new UserLanguage(
                        tlDto.getProficiency(),
                        UserLanguageType.TEACH,
                        user,
                        languageObj.get());
                userLanguageRepository.save(newTeachLanguage);
            }
        }

        for (UserLanguageDTO llDto: userDTO.getLearnLanguages()) {
            Optional<Language> languageObj = languageRepository.findById(llDto.getLanguageId());
            if (languageObj.isPresent()) {
                UserLanguage newLearnLanguage = new UserLanguage(
                        llDto.getProficiency(),
                        UserLanguageType.LEARN,
                        user,
                        languageObj.get());
                userLanguageRepository.save(newLearnLanguage);
            }
        }

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
