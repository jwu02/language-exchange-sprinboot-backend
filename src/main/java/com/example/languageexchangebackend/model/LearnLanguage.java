package com.example.languageexchangebackend.model;

import javax.persistence.*;

@Entity
public class LearnLanguage {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(name = "proficiency")
    private String proficiency;

    public LearnLanguage(long id, User user, Language language, String proficiency) {
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

    public String getProficiency() {
        return proficiency;
    }

    public void setProficiency(String proficiency) {
        this.proficiency = proficiency;
    }
}
