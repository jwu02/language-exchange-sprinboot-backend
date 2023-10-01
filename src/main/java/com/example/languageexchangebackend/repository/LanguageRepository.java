package com.example.languageexchangebackend.repository;

import com.example.languageexchangebackend.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}
