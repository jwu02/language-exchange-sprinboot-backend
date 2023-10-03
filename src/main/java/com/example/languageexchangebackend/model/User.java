package com.example.languageexchangebackend.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    // https://www.baeldung.com/jpa-many-to-many
    // provide name of field in TeachLanguage which maps the relationship
    @OneToMany(mappedBy = "user")
    private Set<TeachLanguage> teachLanguages;

    @OneToMany(mappedBy = "user")
    private Set<LearnLanguage> learnLanguages;

    @Column(name = "self_introduction")
    private String selfIntroduction;

    public User() {
    }

    public User(long id, String email, String username, String password, LocalDate dob, Gender gender, Set<TeachLanguage> teachLanguages, Set<LearnLanguage> learnLanguages, String selfIntroduction) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.teachLanguages = teachLanguages;
        this.learnLanguages = learnLanguages;
        this.selfIntroduction = selfIntroduction;
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<TeachLanguage> getTeachLanguages() {
        return teachLanguages;
    }

    public void setTeachLanguages(Set<TeachLanguage> teachLanguages) {
        this.teachLanguages = teachLanguages;
    }

    public Set<LearnLanguage> getLearnLanguages() {
        return learnLanguages;
    }

    public void setLearnLanguages(Set<LearnLanguage> learnLanguages) {
        this.learnLanguages = learnLanguages;
    }

    public String getSelfIntroduction() {
        return selfIntroduction;
    }

    public void setSelfIntroduction(String selfIntroduction) {
        this.selfIntroduction = selfIntroduction;
    }
}
