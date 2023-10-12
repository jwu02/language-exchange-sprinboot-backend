package com.example.languageexchangebackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "name_in_language")
    private String nameInLanguage;

    @OneToMany(mappedBy = "language")
//    @JsonIgnore // exclude property from serializing
    @JsonBackReference
    private Set<UserLanguage> userLanguages = new HashSet<>();

    public Language() {
    }

    public Language(String name, String code, String nameInLanguage) {
        this.name = name;
        this.code = code;
        this.nameInLanguage = nameInLanguage;
    }

    public Language(long id, String name, String code, String nameInLanguage, Set<UserLanguage> userLanguages) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.nameInLanguage = nameInLanguage;
        this.userLanguages = userLanguages;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNameInLanguage() {
        return nameInLanguage;
    }

    public void setNameInLanguage(String nameInLanguage) {
        this.nameInLanguage = nameInLanguage;
    }

    public Set<UserLanguage> getUserLanguages() {
        return userLanguages;
    }

    public void setUserLanguages(Set<UserLanguage> userLanguages) {
        this.userLanguages = userLanguages;
    }

    public void addUserLanguage(UserLanguage userLanguage) {
        this.userLanguages.add(userLanguage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Language language = (Language) o;
        return name.equals(language.name) && code.equals(language.code) && nameInLanguage.equals(language.nameInLanguage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, nameInLanguage);
    }
}
