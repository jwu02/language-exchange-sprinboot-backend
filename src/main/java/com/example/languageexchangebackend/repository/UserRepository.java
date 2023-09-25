package com.example.languageexchangebackend.repository;

import com.example.languageexchangebackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository internally exchanged alot of interfaces
    // so we can leverage all those methods (including CRUD, pagination, ...)
    // we don't need to create all those methods here

    Optional<User> findOneByEmailAndPassword(String email, String password);
    User findByEmail(String email);
}
