package com.example.languageexchangebackend.service;

import com.example.languageexchangebackend.model.Language;

import java.util.Iterator;
import java.util.List;

public interface LanguageService {
    public List<Language> save(List<Language> languages);
    public boolean hasLanguageRecords();
}
