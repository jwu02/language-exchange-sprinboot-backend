package com.example.languageexchangebackend.controller;

import com.example.languageexchangebackend.model.Language;
import com.example.languageexchangebackend.repository.LanguageRepository;
import com.example.languageexchangebackend.service.LanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/language")
public class LanguageController {
    private final LanguageService languageService;
    private final LanguageRepository languageRepository;

    public LanguageController(
            LanguageService languageService,
            LanguageRepository languageRepository) {
        this.languageService = languageService;
        this.languageRepository = languageRepository;
    }

    @GetMapping("/exchangeable-languages")
    public ResponseEntity<?> getExchangeableLanguages() {
        List<Language> languages = languageRepository.findAll();
        return ResponseEntity.ok(languages);
    }
}
