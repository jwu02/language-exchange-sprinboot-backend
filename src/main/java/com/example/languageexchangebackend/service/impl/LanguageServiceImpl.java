package com.example.languageexchangebackend.service.impl;

import com.example.languageexchangebackend.model.Language;
import com.example.languageexchangebackend.repository.LanguageRepository;
import com.example.languageexchangebackend.service.LanguageService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> save(List<Language> languages) {
        return languageRepository.saveAll(languages);
    }

    @Override
    public boolean hasLanguageRecords() {
        return languageRepository.count() > 0;
    }
}
