package com.example.languageexchangebackend.model;

import javax.persistence.*;

@Entity
public class TeachLanguage {
    @Id
    private long id;

    // many instances of this entity are mapped to one instance of another entity
    // creating a bidirectional relationship with User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

//    @Column(name = "proficiency")
    private LanguageProficiency proficiency;

    public TeachLanguage(long id, User user, Language language, LanguageProficiency proficiency) {
        this.id = id;
        this.user = user;
        this.language = language;
        this.proficiency = proficiency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public LanguageProficiency getProficiency() {
        return proficiency;
    }

    public void setProficiency(LanguageProficiency proficiency) {
        this.proficiency = proficiency;
    }
}
