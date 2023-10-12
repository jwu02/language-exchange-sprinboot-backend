package com.example.languageexchangebackend.repository;

import com.example.languageexchangebackend.model.UserLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface UserLanguageRepository extends JpaRepository<UserLanguage, UserLanguage.Id> {
}
