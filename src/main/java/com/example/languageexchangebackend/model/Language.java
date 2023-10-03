package com.example.languageexchangebackend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "languages")
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
    @JsonIgnore // exclude property from serializing
    private Set<TeachLanguage> teachers;

    @OneToMany(mappedBy = "language")
    @JsonIgnore
    private Set<LearnLanguage> learners;

    public Language() {
    }

    public Language(String name, String code, String nameInLanguage, Set<TeachLanguage> teachers, Set<LearnLanguage> learners) {
        super();
//        this.id = id;
        this.name = name;
        this.code = code;
        this.nameInLanguage = nameInLanguage;
        this.teachers = teachers;
        this.learners = learners;
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

    public Set<TeachLanguage> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<TeachLanguage> teachers) {
        this.teachers = teachers;
    }

    public Set<LearnLanguage> getLearners() {
        return learners;
    }

    public void setLearners(Set<LearnLanguage> learners) {
        this.learners = learners;
    }
}
